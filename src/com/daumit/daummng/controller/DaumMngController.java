package com.daumit.daummng.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

@Controller
public class DaumMngController {
	
	private Log log = null;
	
	public DaumMngController() {
		log = LogFactory.getLog(getClass());
	}
	
	@RequestMapping(value = "/main")
	public String main() {
		log.info("console - main");
		
		return "main";
	}
	
	// 아이바티스 연습
	@RequestMapping(value = "/iBatis")
	public ModelAndView iBatis(@RequestParam HashMap<String, Object> hashMap) {
		log.info("console - iBatis");
		
		DaumMngService service = new DaumMngService();
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		// pass list
		List<Integer> resultList = service.selectCopy(list);
		System.out.println(resultList);
		
		// retrieve list
		List<Map<String, Object>> resultListMap = service.selectBBS();
		System.out.println(resultListMap);
		
		// retrieve map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("useYN", "Y");
		Map<String, Object> resultMap = service.selectAuth(map);
		System.out.println(resultMap);
		
		// retrieve integer
		int resultInt = service.selectUser("레피아");
		System.out.println(resultInt);
		
		// procedure
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("p_usr_id", "admin1");
		boolean resultBoolean = service.insertFile(pMap);
		System.out.println(resultBoolean);
		
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
//			filePath = filePath.replace(request.getContextPath().substring(1), "");
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
}
