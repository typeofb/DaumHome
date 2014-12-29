package com.daumit.sysmng.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.common.MenuController;
import com.common.model.User;
import com.daumit.sysmng.service.CodeMngService;

@Controller
@SessionAttributes("user")
public class CodeMngController extends MenuController {
	
	private Log log = null;
	private CodeMngService codeMngService;
	
	public CodeMngController() {
		log = LogFactory.getLog(getClass());
		codeMngService = new CodeMngService();
	}
	
	// 본부
	@RequestMapping(value = "codeArea")
	public ModelAndView codeArea(@ModelAttribute User user) { // 세션체크
		log.info("console - codeArea");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sysMng/codeMng/codeArea");
		return mav;
	}
	
	// 본부 검색
	@RequestMapping(value = "codeAreaSearch")
	public ModelAndView codeAreaSearch() {
		log.info("console - codeAreaSearch");
		
		List<HashMap<String, Object>> list = codeMngService.selectAreaList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("sysMng/codeMng/codeAreaAjax");
		return mav;
	}
	
	// 본부 등록
	@RequestMapping(value = "codeAreaReg")
	public ModelAndView codeAreaReg(HttpServletResponse response,
			@RequestParam HashMap<String, Object> iMaps) throws IOException {
		log.info("console - codeAreaReg");
		
		boolean result = codeMngService.insertArea(iMaps);
		String strMessage = null;
		if (result) {
			strMessage = "등록되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		List<HashMap<String, Object>> list = codeMngService.selectAreaList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.setViewName("sysMng/codeMng/codeAreaAjax");
		return mav;
	}
	
	// 본부 수정
	@RequestMapping(value = "codeAreaMod")
	public ModelAndView codeAreaMod(HttpServletResponse response,
			@RequestParam HashMap<String, Object> iMaps) throws IOException {
		log.info("console - codeAreaMod");
		
		boolean result = codeMngService.updateArea(iMaps);
		String strMessage = null;
		if (result) {
			strMessage = "수정되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		List<HashMap<String, Object>> list = codeMngService.selectAreaList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.setViewName("sysMng/codeMng/codeAreaAjax");
		return mav;
	}
	
	// 본부 삭제
	@RequestMapping(value = "codeAreaDel")
	public ModelAndView codeAreaDel(HttpServletResponse response,
			@RequestParam(value="areaCd", required=true) String areaCd) throws IOException {
		log.info("console - codeAreaDel");
		
		boolean result = codeMngService.deleteArea(areaCd);
		String strMessage = null;
		if (result) {
			strMessage = "삭제되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}

		List<HashMap<String, Object>> list = codeMngService.selectAreaList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.setViewName("sysMng/codeMng/codeAreaAjax");
		return mav;
	}
}
