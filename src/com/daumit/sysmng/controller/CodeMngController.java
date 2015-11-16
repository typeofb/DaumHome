package com.daumit.sysmng.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	@RequestMapping(value = "code")
	public ModelAndView code(@ModelAttribute("sessionCheck") User user) { // 세션체크
		log.info("console - code");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sysMng/codeMng/code");
		return mav;
	}
	
	// 본부 검색
	@RequestMapping(value = "codeSearch")
	public ModelAndView codeSearch(@RequestParam(value="targetPage", required=false) String targetPageStr) {
		log.info("console - codeSearch");
		
		int targetPage = Integer.parseInt(Common.NVL(targetPageStr, "1"));
		List<Map<String, Object>> list = codeMngService.selectCodeList(targetPage);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("targetPage", targetPage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, targetPage, 10));
		mav.setViewName("sysMng/codeMng/codeAjax");
		return mav;
	}
	
	// 본부 등록
	@RequestMapping(value = "codeReg")
	public ModelAndView codeReg(HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap) throws IOException {
		log.info("console - codeReg");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		paramMap.put("lastUpdateDtime", sdf.format(new Date(System.currentTimeMillis())));
		boolean result = codeMngService.insertCode(paramMap);
		String strMessage = null;
		if (result) {
			strMessage = "등록되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		List<Map<String, Object>> list = codeMngService.selectCodeList(Integer.valueOf(paramMap.get("targetPage").toString()));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(paramMap.get("targetPage").toString()), 10));
		mav.setViewName("sysMng/codeMng/codeAjax");
		return mav;
	}
	
	// 본부 수정
	@RequestMapping(value = "codeMod")
	public ModelAndView codeMod(HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap) throws IOException {
		log.info("console - codeMod");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		paramMap.put("lastUpdateDtime", sdf.format(new Date(System.currentTimeMillis())));
		boolean result = codeMngService.updateCode(paramMap);
		String strMessage = null;
		if (result) {
			strMessage = "수정되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		List<Map<String, Object>> list = codeMngService.selectCodeList(Integer.valueOf(paramMap.get("targetPage").toString()));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(paramMap.get("targetPage").toString()), 10));
		mav.setViewName("sysMng/codeMng/codeAjax");
		return mav;
	}
	
	// 본부 삭제
	@RequestMapping(value = "codeDel")
	public ModelAndView codeDel(HttpServletResponse response,
			@RequestParam Map<String, Object> paramMap,
			@RequestParam(value="targetPage", required=false) String targetPage) throws IOException {
		log.info("console - codeDel");
		
		boolean result = codeMngService.deleteCode(paramMap);
		String strMessage = null;
		if (result) {
			strMessage = "삭제되었습니다.";
		} else {
			strMessage = "실패하였습니다.";
		}
		
		new Exception();
		
		List<Map<String, Object>> list = codeMngService.selectCodeList(Integer.valueOf(targetPage));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("strMessage", strMessage);
		mav.addObject("paging", Common.paging(Integer.valueOf(list.get(0).get("TOTAL_ROW_SIZE").toString()), 10, Integer.valueOf(targetPage), 10));
		mav.setViewName("sysMng/codeMng/codeAjax");
		return mav;
	}
}
