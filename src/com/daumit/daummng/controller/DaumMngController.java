package com.daumit.daummng.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.common.Common;
import com.daumit.daummng.service.DaumMngService;

@Controller
public class DaumMngController {

	private Log log = LogFactory.getLog(getClass());

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

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postId", 273);
		map.put("usrgrdCd", 30);

		// retrieve list
		List<Map<String, Object>> resultList = service.selectBBS(map);
		System.out.println(resultList);

		// retrieve map
		Map<String, Object> resultMap = service.selectAuth(map);
		System.out.println(resultMap);

		// retrieve integer
		int resultInt = service.selectUser("약정수용가");
		System.out.println(resultInt);

		// procedure
		HashMap<String, String> pMap = new HashMap<String, String>();
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

	// 게시판
	@RequestMapping(value = "/bbs")
	public String bbs() {
		log.info("console - bbs");

		return "bbs";
	}

	// 게시판 사진, 파일 업로드
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/daumeditor/pages/trex/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		log.info("console - upload");

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> mFiles = multipartRequest.getFiles("attachment");

		JSONArray jsonArray = new JSONArray();

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

			JSONObject jsonObj = new JSONObject();
			jsonObj.put("FILE_NM", fileName);
			jsonObj.put("FILE_SIZE", fileSize);
			jsonObj.put("URL_PATH", "upload/" + today + "/" + fileName);

			jsonArray.add(jsonObj);
		}

		PrintWriter printwriter = null;
		response.setContentType("text/html;charset=UTF-8");
		try {
			printwriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		printwriter.print(jsonArray);
	}

	// 게시판 등록
	@RequestMapping(value = "/insertBbs")
	public String insertBbs(@RequestParam HashMap<String, Object> param) {
		log.info("console - insertBbs");

		System.out.println(param);
		return "bbs";
	}
}
