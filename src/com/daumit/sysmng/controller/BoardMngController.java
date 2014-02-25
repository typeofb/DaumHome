package com.daumit.sysmng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.common.Common;
import com.daumit.sysmng.service.BoardMngService;

@Controller
public class BoardMngController {
	
	private Log log = null;
	private BoardMngService boardMngService;
	
	public BoardMngController() {
		log = LogFactory.getLog(getClass());
		boardMngService = new BoardMngService();
	}
	
	// 게시판
	@RequestMapping(value = "boardList")
	public ModelAndView boardList(@RequestParam(value="rowSize", required=false) String rowSizeStr,
			@RequestParam(value="targetPage", required=false) String targetPageStr,
			@RequestParam(value="pageGroupSize", required=false) String pageGroupSizeStr,
			@RequestParam(value="beginDate", required=false) String beginDate,
			@RequestParam(value="endDate", required=false) String endDate) {
		log.info("console - boardList");
		
		if (beginDate == null || beginDate.equals("")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			endDate = formatter.format(cal.getTime());
			cal.add(Calendar.DATE, -7);
			beginDate = formatter.format(cal.getTime());
		}
		
		int totalRowSize = 0;
		int rowSize = Integer.parseInt(Common.NVL(rowSizeStr, "15"));
		int targetPage = Integer.parseInt(Common.NVL(targetPageStr, "1"));
		int pageGroupSize = Integer.parseInt(Common.NVL(pageGroupSizeStr, "10"));
		
		HashMap<String, Object> iMaps = new HashMap<String, Object>();
		iMaps.put("beginDate", beginDate.replaceAll("-", ""));
		iMaps.put("endDate", endDate.replaceAll("-", ""));
		iMaps.put("targetPage", Integer.valueOf((targetPage - 1) * rowSize));
		iMaps.put("rowSize", Integer.valueOf(rowSize));
		
		List<HashMap<String, Object>> list = boardMngService.selectBoardList(iMaps);
		if (list.size() > 0) {
			totalRowSize = boardMngService.selectBoardCnt(iMaps);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("beginDate", beginDate);
		mav.addObject("endDate", endDate);
		mav.addObject("list", list);
		mav.addObject("paging", Common.paging(totalRowSize, rowSize, targetPage, pageGroupSize));
		mav.setViewName("sysMng/boardMng/boardList");
		return mav;
	}
	
	// 게시판 상세보기
	@RequestMapping(value = "boardDetail")
	public ModelAndView boardDetail(@RequestParam(value="postId", required=true) String postId) {
		log.info("console - boardDetail");
		
		HashMap<String, Object> map = boardMngService.selectBoardDetail(postId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("sysMng/boardMng/boardDetail");
		return mav;
	}
	
	// 게시판 등록으로 이동
	@RequestMapping(value = "goReg")
	public ModelAndView goReg() {
		log.info("console - goReg");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sysMng/boardMng/boardReg");
		return mav;
	}
	
	// 게시판 등록
	@RequestMapping(value = "boardReg")
	public ModelAndView boardReg(HttpServletResponse response,
			@RequestParam HashMap<String, Object> iMaps) throws IOException {
		log.info("console - boardReg");
		
		boolean result = false;
		result = boardMngService.insertBoard(iMaps);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		return null;
	}
	
	// 게시판 수정으로 이동
	@RequestMapping(value = "goMod")
	public ModelAndView goMod(@RequestParam(value="postId", required=true) String postId) {
		log.info("console - goMod");
		
		HashMap<String, Object> map = boardMngService.selectBoardDetail(postId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("sysMng/boardMng/boardMod");
		return mav;
	}
	
	// 게시판 수정
	@RequestMapping(value = "boardMod")
	public ModelAndView boardMod(HttpServletResponse response,
			@RequestParam HashMap<String, Object> iMaps) throws IOException {
		log.info("console - boardMod");
		
		boolean result = false;
		result = boardMngService.updateBoard(iMaps);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		return null;
	}
	
	// 게시판 삭제
	@RequestMapping(value = "boardDel")
	public ModelAndView boardDel(HttpServletResponse response,
			@RequestParam(value="postId", required=true) String postId) throws IOException {
		log.info("console - boardDel");
		
		boolean result = false;
		result = boardMngService.deleteBoard(postId);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		return null;
	}
}
