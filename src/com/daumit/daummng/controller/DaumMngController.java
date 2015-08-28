package com.daumit.daummng.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.common.Common;
import com.daumit.daummng.service.DaumMngService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

@Controller
public class DaumMngController {
	
	private Log log = null;
	
	public DaumMngController() {
		log = LogFactory.getLog(getClass());
	}
	
	@Resource(name="DaumMngService")
	private DaumMngService daumMngService;
	
	@RequestMapping(value = "/main")
	public String main() {
		log.info("console - main");
		
		return "main";
	}
	
	// 아이바티스 연습
	@RequestMapping(value = "/iBatis")
	public ModelAndView iBatis(@RequestParam HashMap<String, Object> hashMap) {
		log.info("console - iBatis");
		
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		// pass list
		List<Map<String, Object>> resultList = daumMngService.selectCopy(list);
		System.out.println(resultList);
		
		// retrieve list
		List<Map<String, Object>> resultListMap = daumMngService.selectBBS();
		System.out.println(resultListMap);
		
		// retrieve map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("useYN", "Y");
		map.put("sortOrder", "0");
		Map<String, Object> resultMap = daumMngService.selectAuth(map);	// 반드시 결과 row가 하나이어야 한다.
		System.out.println(resultMap);
		
		// retrieve integer
		int resultInt = daumMngService.selectUser("관리자");
		System.out.println(resultInt);
		
		// procedure
//		Map<String, String> pMap = new HashMap<String, String>();
//		pMap.put("p_usr_id", "admin1");
//		boolean resultBoolean = daumMngService.insertFile(pMap);
//		System.out.println(resultBoolean);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
	}
	
	// 제어관리
	@RequestMapping(value = "/controlMng")
	public String controlMng() {
		log.info("console - controlMng");
		
		return "controlMng";
	}
	
	// 예약제어
	@RequestMapping(value = "/commandControl")
	public ModelAndView commandControl(HttpServletRequest request) {
		log.info("console - commandControl");
		
		Socket socket = null;
		
		try {
			InetAddress local = InetAddress.getLocalHost();
			socket = new Socket(local.getHostAddress(), 3000);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			// ① Web -> Server
			byte[] sendResultArr = new byte[32];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(baos);
			
			out.writeByte(0x41);						// Command A
			out.write(182); 							// 회차 번호
			out.write(1);								// 1: 단일 전송, 2: 다수 전송
			out.write(Common.intToTwoByteArray(8052)); 	// DC ID, 1byte 최대값 255를 넘어서 2byte를 사용[최대값:65536(2^16 -1)]
			
			sendResultArr = baos.toByteArray();
			dos.write(sendResultArr);
			dos.flush();
			
			// dis.read()이 3초간 응답없으면 SocketTimeoutException
			socket.setSoTimeout(3000);
			
			// ④ Server -> Web
			byte[] buffer = new byte[128];
			byte[] result = null;
			int leftBufferSize = 0;
			leftBufferSize = dis.read(buffer, 0, buffer.length);
			result = new byte[leftBufferSize];
			for (int i = 0; i < result.length; i++) {
				result[i] = buffer[i];
			}
			
			System.out.println((char) result[0]); 						// Command A
			System.out.println(Common.twoByteArrayToInt(result, 1)); 	// DC ID
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("controlMng");
		return mav;
	}
	
	// 다음에디터
	@RequestMapping(value = "/bbs")
	public String bbs() {
		log.info("console - bbs");
		
		return "bbs";
	}
	
	// 다음에디터 사진, 파일 업로드
	@RequestMapping(value = "/daumeditor/pages/trex/upload")
	public @ResponseBody ResponseEntity<List<Map<String, Object>>> upload(HttpServletRequest request) {
		log.info("console - upload");
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> mFiles = multipartRequest.getFiles("attachment");
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		
		for (int i = 0; i < mFiles.size(); i++) {
			MultipartFile mFile = mFiles.get(i);
			
			String fileName = mFile.getOriginalFilename();
			int fileSize = (int) mFile.getSize();
			
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
			String today = f.format(new Date());
			
			String filePath = request.getServletContext().getRealPath("");
			filePath = filePath.replace(request.getContextPath().substring(1), "");
			filePath = filePath + "\\upload\\" + today + "\\";
			File file = new File(filePath + fileName);
			try {
				FileCopyUtils.copy(mFile.getInputStream(), new FileOutputStream(file));
			} catch (Exception e) {
				log.error(e);
				File folder = new File(filePath);
				folder.mkdirs();
				try {
					FileCopyUtils.copy(mFile.getInputStream(), new FileOutputStream(file));
				} catch (Exception e1) {
					log.error(e1);
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FILE_NM", fileName);
			map.put("FILE_SIZE", fileSize);
			map.put("URL_PATH", "upload/" + today + "/" + fileName);
			
			listMap.add(map);
		}
		
		// IE에서 다운로드 팝업 나타나는 현상 수정
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<List<Map<String, Object>>>(listMap, responseHeaders, HttpStatus.CREATED);
	}
	
	// 다음에디터 등록
	@RequestMapping(value = "/insertBbs")
	public String insertBbs(@RequestParam HashMap<String, Object> param) {
		log.info("console - insertBbs");
		
		System.out.println(param);
		return "bbs";
	}
	
	// 모바일
	@RequestMapping(value = "/mobile")
	public String mobile() {
		log.info("console - mobile");
		
		return "mobile/mobile";
	}
	
	// 비 HTML
	@RequestMapping(value = "/nonHtml")
	public String nonHtml() {
		log.info("console - nonHtml");
		
		return "nonHtml/nonHtml";
	}
	
	private void downloadCommon(HttpServletResponse response, String path)
			throws IOException, FileNotFoundException {
		File file = new File(path);
		response.setContentType("application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + file.getName() + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ex) {
				}
		}
		out.flush();
	}
	
	@RequestMapping("/file")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("console - file");
		
		String path = request.getServletContext().getRealPath("/WEB-INF/web.xml");
		
		downloadCommon(response, path);
	}
	
	@RequestMapping("/excel")
	public void downloadExcel(HttpServletResponse response) throws Exception {
		log.info("console - excel");
		
		List<String> pageRanks = new ArrayList<String>();
		pageRanks.add(new String("/bbs/telzone/list"));
		pageRanks.add(new String("/bbs/humor/list"));
		pageRanks.add(new String("/bbs/agora/list"));
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = createFirstSheet(workbook);
		createColumnLabel(sheet);
		
		short rowNum = 1;
		for (String rank : pageRanks) {
			createPageRankRow(sheet, rank, rowNum++);
		}
		
		String path = "C:\\workbook.xls";
		FileOutputStream fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();
		
		downloadCommon(response, path);
	}
	
	private Sheet createFirstSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet("Report1");
		sheet.setColumnWidth((short) 1, (short) (256 * 20));
		return sheet;
	}
	
	private void createColumnLabel(Sheet sheet) {
		Row firstRow = sheet.createRow((short) 0);
		Cell cell = firstRow.createCell((short) 0);
		cell.setCellValue("첫번째 열제목");
		
		cell = firstRow.createCell((short) 1);
		cell.setCellValue("두번째 열제목");
	}
	
	private void createPageRankRow(Sheet sheet, String rank, short rowNum) {
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell((short) 0);
		cell.setCellValue(rank);
		
		cell = row.createCell((short) 1);
		cell.setCellValue(rank);
	}
	
	@RequestMapping("/pdf")
	public void downloadPdf(HttpServletResponse response) throws Exception {
		log.info("console - pdf");
		
		List<String> pageViews = new ArrayList<String>();
		pageViews.add(new String("/bbs/telzone/list"));
		pageViews.add(new String("/bbs/humor/list"));
		pageViews.add(new String("/bbs/agora/list"));
		
		Table table = new Table(2, pageViews.size() + 1);
		table.setPadding(5);
		
		BaseFont bfKorean = BaseFont.createFont(
				  "c:\\windows\\fonts\\batang.ttc,0",
				  BaseFont.IDENTITY_H,
				  BaseFont.EMBEDDED);
		
		Font font = new Font(bfKorean);
		com.lowagie.text.Cell cell = new com.lowagie.text.Cell(new Paragraph("첫번째 열제목", font));
		cell.setHeader(true);
		table.addCell(cell);
		cell = new com.lowagie.text.Cell(new Paragraph("두번째 열제목", font));
		table.addCell(cell);
		table.endHeaders();
		
		for (String view : pageViews) {
			table.addCell(view);
			table.addCell(view);
		}
		
		String path = "C:\\iText.pdf";
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(path));
		document.open();
		document.add(table);
		document.close();
		
		downloadCommon(response, path);
	}
}
