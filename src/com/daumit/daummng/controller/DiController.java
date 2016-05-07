package com.daumit.daummng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.daumit.daummng.dao.ICommonDao;

public class DiController implements Controller {

	// 1. DiController가 OracleDaoImpl에 의존한다.(X)
//	OracleDaoImpl dao = new OracleDaoImpl();
	
	// 2. 다형성을 이용한다.(X)
	private ICommonDao dao; // = new OracleDaoImpl();
	
	// 3. 스프링이 생성한 객체를 주입받아야 하므로 setter 메서드가 필요하다.(O)
	public void setDao(ICommonDao dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		dao.select("메시지");
		dao.insert("메시지");
		dao.update("메시지");
		dao.delete("메시지");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
	}
}
