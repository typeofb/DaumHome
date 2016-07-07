package com.daumit.sysmng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.common.etc.Common;
import com.common.controller.MenuController;
import com.common.model.User;
import com.components.codevalue.CodeValueCache;
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
	@RequestMapping(value = "code")
	public ModelAndView code(@ModelAttribute("sessionCheck") User user) { // 세션체크
		log.info("console - code");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("targetPage", 1);
		mav.setViewName("sysMng/codeMng/code");
		return mav;
	}
	
	// 본부 검색
	@RequestMapping(value = "codeSearch")
	public ModelAndView codeSearch(@RequestParam(value="targetPage", required=false) String targetPage) {
		log.info("console - codeSearch");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("targetPage", targetPage);
		paramMap.put("rowSize", 10);
		List<Map<String, Object>> list = codeMngService.selectCodeList(paramMap);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("targetPage", targetPage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(targetPage), 10));
		mav.setViewName("sysMng/codeMng/codeAjax");
		return mav;
	}
	
	// CodeValue 보기
	@RequestMapping(value = "codeView")
	public void codeView(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("console - codeView");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(CodeValueCache.get());
		out.println("<br />");
		out.println("<br />");
		out.println("<a href='" + request.getContextPath() + "/code.do" + "'>코드관리 검색</a>");
		out.flush();
	}
	
	// 본부 등록
	@RequestMapping(value = "codeReg")
	public ModelAndView codeReg(@RequestParam Map<String, Object> paramMap) throws IOException {
		log.info("console - codeReg");
		
		boolean result = codeMngService.insertCode(paramMap);
		String strMessage = null;
		if (result) {
			strMessage = "등록되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		paramMap.put("rowSize", 10);
		List<Map<String, Object>> list = codeMngService.selectCodeList(paramMap);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(paramMap.get("targetPage").toString()), 10));
		mav.setViewName("sysMng/codeMng/codeAjax");
		return mav;
	}
	
	// 본부 수정
	@RequestMapping(value = "codeMod")
	public ModelAndView codeMod(@RequestParam Map<String, Object> paramMap) throws IOException {
		log.info("console - codeMod");
		
		boolean result = codeMngService.updateCode(paramMap);
		String strMessage = null;
		if (result) {
			strMessage = "수정되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		paramMap.put("rowSize", 10);
		List<Map<String, Object>> list = codeMngService.selectCodeList(paramMap);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(paramMap.get("targetPage").toString()), 10));
		mav.setViewName("sysMng/codeMng/codeAjax");
		return mav;
	}
	
	// 본부 삭제
	@RequestMapping(value = "codeDel")
	public ModelAndView codeDel(@RequestParam Map<String, Object> paramMap) throws IOException {
		log.info("console - codeDel");
		
		boolean result = codeMngService.deleteCode(paramMap);
		String strMessage = null;
		if (result) {
			strMessage = "삭제되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		paramMap.put("rowSize", 10);
		List<Map<String, Object>> list = codeMngService.selectCodeList(paramMap);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(paramMap.get("targetPage").toString()), 10));
		mav.setViewName("sysMng/codeMng/codeAjax");
		return mav;
	}
}
