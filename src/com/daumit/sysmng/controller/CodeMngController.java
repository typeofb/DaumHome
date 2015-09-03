package com.daumit.sysmng.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.common.Common;
import com.common.MenuController;
import com.common.model.User;
import com.daumit.sysmng.service.CodeMngService;

@SessionAttributes("sessionCheck")
@Controller
public class CodeMngController extends MenuController {
	
	private Log log = null;
	
	public CodeMngController() {
		log = LogFactory.getLog(getClass());
	}
	
	@Resource(name="CodeMngService")
	private CodeMngService codeMngService;
	
	// 본부
	@RequestMapping(value = "codeArea")
	public ModelAndView codeArea(@ModelAttribute("sessionCheck") User user) { // 세션체크
		log.info("console - codeArea");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sysMng/codeMng/codeArea");
		return mav;
	}
	
	// 본부 검색
	@RequestMapping(value = "codeAreaSearch")
	public ModelAndView codeAreaSearch(@RequestParam(value="targetPage", required=false) String targetPageStr) {
		log.info("console - codeAreaSearch");
		
		int targetPage = Integer.parseInt(Common.NVL(targetPageStr, "1"));
		List<Map<String, Object>> list = codeMngService.selectAreaList(targetPage);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("targetPage", targetPage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, targetPage, 10));
		mav.setViewName("sysMng/codeMng/codeAreaAjax");
		return mav;
	}
	
	// 본부 등록
	@RequestMapping(value = "codeAreaReg")
	public ModelAndView codeAreaReg(HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap) throws IOException {
		log.info("console - codeAreaReg");
		
		boolean result = codeMngService.insertArea(paramMap);
		String strMessage = null;
		if (result) {
			strMessage = "등록되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		List<Map<String, Object>> list = codeMngService.selectAreaList(Integer.valueOf(paramMap.get("targetPage").toString()));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(paramMap.get("targetPage").toString()), 10));
		mav.setViewName("sysMng/codeMng/codeAreaAjax");
		return mav;
	}
	
	// 본부 수정
	@RequestMapping(value = "codeAreaMod")
	public ModelAndView codeAreaMod(HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap) throws IOException {
		log.info("console - codeAreaMod");
		
		boolean result = codeMngService.updateArea(paramMap);
		String strMessage = null;
		if (result) {
			strMessage = "수정되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		List<Map<String, Object>> list = codeMngService.selectAreaList(Integer.valueOf(paramMap.get("targetPage").toString()));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(paramMap.get("targetPage").toString()), 10));
		mav.setViewName("sysMng/codeMng/codeAreaAjax");
		return mav;
	}
	
	// 본부 삭제
	@RequestMapping(value = "codeAreaDel")
	public ModelAndView codeAreaDel(HttpServletResponse response,
			@RequestParam(value="userID", required=true) String userID,
			@RequestParam(value="targetPage", required=false) String targetPage) throws IOException {
		log.info("console - codeAreaDel");
		
		boolean result = codeMngService.deleteArea(userID);
		String strMessage = null;
		if (result) {
			strMessage = "삭제되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		new Exception();
		
		List<Map<String, Object>> list = codeMngService.selectAreaList(Integer.valueOf(targetPage));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(targetPage), 10));
		mav.setViewName("sysMng/codeMng/codeAreaAjax");
		return mav;
	}
}
