package com.daumit.daummng.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.common.Common;
import com.daumit.daummng.service.DaumMngService;

@Controller
public class DaumMngController {

	private Log log = LogFactory.getLog(getClass());
	private DaumMngService service = new DaumMngService();
	private Socket socket = null;

	@RequestMapping(value = "/main")
	public String main() {

		log.info("console - main");

		return "main";
	}
	
	// 아이바티스 연습
	@RequestMapping(value = "/iBatis")
	public ModelAndView iBatis(@RequestParam HashMap<String, Object> hashMap) {

		log.info("console - iBatis");

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
			out.write(182);								// 회차 번호
			out.write(1);								// 1: 단일 전송, 2: 다수 전송
			out.write(Common.intToTwoByteArray(8052));	// DC ID, 1byte 최대값 255를 넘어서 2byte를 사용[최대값:65536(2^16 -1)]
			
			sendResultArr = baos.toByteArray();
			dos.write(sendResultArr);
			dos.flush();
			
			//socket.setSoTimeout(3000);
			
			// ④ Server -> Web
			while (dis != null) {
				byte[] buffer = new byte[128];
				byte[] result = null;
				int leftBufferSize = 0;
				while ((leftBufferSize = dis.read(buffer, 0, buffer.length)) != -1) {
					result = new byte[leftBufferSize];
					for (int i = 0; i < result.length; i++) {
						result[i] = buffer[i];
					}
					
					System.out.println((char) result[0]);						// Command A
					System.out.println(Common.twoByteArrayToInt(result, 1));	// DC ID
					break;
				}
				break;
			}
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
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
	
	@RequestMapping(value = "/bbs")
	public String bbs() {

		log.info("console - bbs");

		return "bbs";
	}
}
