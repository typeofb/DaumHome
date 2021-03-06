package com.common.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.common.model.User;
import com.common.security.EPUser;

@Controller
@SessionAttributes("sessionCheck")
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
	public ModelAndView loginCheck(User user, Model model) {
		
		log.info("console - loginCheck");
		
		ModelAndView mav = new ModelAndView();
		
		if (!user.getUserId().equals("typeofb")) { // 아이디가 등록되지 않는 경우
			mav.addObject("loginResult", "E3");
		} else {
			if (!user.getUserPw().equals("1234")) { // 패스워드가 틀릴 경우
				mav.addObject("loginResult", "E2");
			} else {
				String userStat = "Y";
				if (!userStat.equals("Y")) { // 아이디가 휴면, 대기상태일 경우
					mav.addObject("loginResult", "E1");
				} else { // 성공일 경우
					mav.addObject("loginResult", "C");
					mav.addObject("userAuth", "00");
					mav.addObject("userId", user.getUserId());
					user.setLoginResult("C");
					user.setUserAuth("00");
					model.addAttribute("sessionCheck", user);
				}
			}
		}
		mav.setViewName("loginCheck");
		return mav;
	}

	// logout
	@RequestMapping(value = "/logout")
	public ModelAndView logout(WebRequest request, SessionStatus status) {
		
		log.info("console - logout");
		
		ModelAndView mav = new ModelAndView();
		status.setComplete();
		request.removeAttribute("sessionCheck", WebRequest.SCOPE_SESSION);
		mav.setViewName("redirect:main.do");
		return mav;
	}
	
	@RequestMapping(value = "login_main")
	public ModelAndView login_main() {
		log.info("login_main");
		return new ModelAndView("login/login_main");
	}
	
	@RequestMapping(value = "logout_main")
	public ModelAndView logout_main() {
		log.info("logout_main");
		return new ModelAndView("login/logout_main");
	}
	
	@RequestMapping(value = "login_success")
	public ModelAndView login_success() {
		log.info("login_success");
		EPUser user = (EPUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
		System.out.println(user);
		return new ModelAndView("login/login_success");
	}
	
	@RequestMapping(value = "login_duplicate")
	public ModelAndView login_duplicate() {
		log.info("login_duplicate");
		return new ModelAndView("login/login_duplicate");
	}
}
