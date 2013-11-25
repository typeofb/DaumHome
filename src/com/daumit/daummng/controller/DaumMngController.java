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
	public ModelAndView main(@RequestParam HashMap<String, Object> hashMap) {

		log.info("console - main");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postId", 273);
		map.put("usrgrdCd", 30);

		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", hashMap.get("userId"));
		mav.setViewName("main");
		return mav;
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
			out.write(Common.intToFourByteArray(8052));	// DC ID
			
			sendResultArr = baos.toByteArray();
			dos.write(sendResultArr);
			dos.flush();
			
			//socket.setSoTimeout(3000);
			
			// ④ Server -> Web
			while (dis != null) {
				int size = 0;
				byte[] buffer = new byte[128];
				if ((size = dis.read(buffer)) != -1) {
					System.out.println((char) buffer[0]);						// Command A
					System.out.println(Common.twoByteArrayToInt(buffer, 1));	// DC ID
					break;
				}
			}
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("controlMng");
		return mav;
	}
}
