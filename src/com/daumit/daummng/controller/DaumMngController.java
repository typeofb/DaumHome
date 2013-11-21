package com.daumit.daummng.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		mav.addObject("userId", hashMap.get("userId"));
		mav.setViewName("main");
		return mav;
	}

	// login
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		
		log.info("console - login");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	// loginCheck
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public ModelAndView loginCheck(@RequestParam HashMap<String, Object> map, HttpSession session) throws Exception {

		log.info("console - loginCheck");
		
		ModelAndView mav = new ModelAndView();

		if (!map.get("userId").equals("typeofb")) { // 아이디가 등록되지 않는 경우
			mav.addObject("loginResult", "E3");
			session.setAttribute("loginResult", "E3");
		} else {
			if (!map.get("userPw").equals("1234")) { // 패스워드가 틀릴 경우
				mav.addObject("loginResult", "E2");
				session.setAttribute("loginResult", "E2");
			} else {
				String userStat = "Y";
				if (!userStat.equals("Y")) { // 아이디가 휴면, 대기상태일 경우
					mav.addObject("loginResult", "E1");
					session.setAttribute("loginResult", "E1");
				} else { // 성공일 경우
					mav.addObject("loginResult", "C");
					session.setAttribute("loginResult", "C");
					
					mav.addObject("userAuth", "00");
					mav.addObject("userId", map.get("userId"));
					session.setAttribute("userId", map.get("userId"));
				}
			}
		}
		mav.setViewName("loginCheck");
		return mav;
	}

	// logout
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		
		log.info("console - logout");
		
		ModelAndView mav = new ModelAndView();
		session.invalidate();
		mav.setViewName("redirect:main.do");
		return mav;
	}
	
	// 제어관리
	@RequestMapping(value = "/controlMng")
	public String controlMng() {
		
		log.info("console - controlMng");
		
		return "controlMng";
	}
	
	// 제어전송
	@RequestMapping(value = "/commandControl")
	public ModelAndView commandControl(HttpServletRequest request) {
		
		log.info("console - commandControl");
		
		try {
			InetAddress local = InetAddress.getLocalHost();
			socket = new Socket(local.getHostAddress(), 3000);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			byte[] sendResultArr = new byte[32];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(baos);
			
			out.writeByte(0x41);	// Command A
			out.write(182);			// 회차 번호
			out.write(1);			// 1: 단일 전송, 2: 다수 전송
			out.write(8052);		// DC ID
			
			sendResultArr = baos.toByteArray();
			dos.write(sendResultArr);
			dos.flush();
			
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
