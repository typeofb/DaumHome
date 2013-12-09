package com.common;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	private Log log = LogFactory.getLog(getClass());
	
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
}