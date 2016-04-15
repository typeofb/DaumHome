package com.daumit.sysmng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.common.etc.Common;
import com.common.dao.ResultSetData;
import com.common.dao.SearchCondition;
import com.daumit.sysmng.service.BoardMngService;

@SuppressWarnings("unchecked")
@Controller
public class BoardMngController {
	
	private Log log = null;
	
	public BoardMngController() {
		log = LogFactory.getLog(getClass());
	}
	
	@Resource(name="BoardMngService")
	private BoardMngService boardMngService;
	
	// 게시판
	@RequestMapping(value = "boardList")
	public ModelAndView boardList(@RequestParam(value="rowSize", required=false) String rowSizeStr,
			@RequestParam(value="targetPage", required=false) String targetPageStr,
			@RequestParam(value="pageGroupSize", required=false) String pageGroupSizeStr,
			@RequestParam(value="sortYn", required=false) String sortYn,
			@RequestParam(value="sortField", required=false) String sortField,
			@RequestParam(value="sortOrderBy", required=false) String sortOrderBy,
			@RequestParam(value="beginDate", required=false) String beginDate,
			@RequestParam(value="endDate", required=false) String endDate,
			@RequestParam(value="selectItem", required=false) String selectItem,
			@RequestParam(value="searchText", required=false) String searchText) throws Exception {
		log.info("console - boardList");
		
		if (beginDate == null || beginDate.equals("")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			endDate = formatter.format(cal.getTime());
			cal.add(Calendar.DATE, -7);
			beginDate = formatter.format(cal.getTime());
		}
		
		int rowSize = Integer.parseInt(Common.NVL(rowSizeStr, "10"));
		int targetPage = Integer.parseInt(Common.NVL(targetPageStr, "1"));
		int pageGroupSize = Integer.parseInt(Common.NVL(pageGroupSizeStr, "10"));
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", "Y");
		
		SearchCondition sc = new SearchCondition();
		sc.addSearchKey("searchText");
		sc.addSearchParam("searchText", selectItem, searchText, "string", "like");
		sc.addSearchKey("beginDate");
		sc.addSearchParam("beginDate", "WBDATE", (beginDate + "~" + endDate), "date", "between");
		if (sortOrderBy == null || sortOrderBy.equals("")) {
		} else if ("ASC".equalsIgnoreCase(sortOrderBy))
			sc.addOrder(sortField, true);
		else
			sc.addOrder(sortField, false);
		sc.setRowSize(rowSize);
		sc.setTargetPage(targetPage);
		sc.setHeaderSortYn(sortYn);
		sc.setHeaderSortField(sortField);
		sc.setHeaderSortOrderBy(sortOrderBy);
		
		ResultSetData list = boardMngService.selectBoardList(paramMap, sc);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("targetPage", targetPage);
		mav.addObject("beginDate", beginDate);
		mav.addObject("endDate", endDate);
		mav.addObject("list", list);
		mav.addObject("rowSize", rowSize);
		mav.addObject("pageGroupSize", pageGroupSize);
		mav.addObject("selectItem", selectItem);
		mav.addObject("searchText", searchText);
		mav.addObject("sortYn", sortYn);
		mav.addObject("sortField", sortField);
		mav.addObject("sortOrderBy", sortOrderBy);
		Map<String, Object> totalRowSize = new HashMap<String, Object>();
		try {
			totalRowSize = (Map<String, Object>) list.get(0);
		} catch (IndexOutOfBoundsException e) {
			totalRowSize.put("H_CNT", 0);
		}
		mav.addObject("paging", Common.paging(Integer.valueOf(String.valueOf(totalRowSize.get("H_CNT"))), rowSize, targetPage, pageGroupSize));
		mav.setViewName("sysMng/boardMng/boardList");
		return mav;
	}
	
	// 게시판 상세보기
	@RequestMapping(value = "boardDetail")
	public ModelAndView boardDetail(@RequestParam Map<String, String> params) {
		log.info("console - boardDetail");
		
		Map<String, Object> map = boardMngService.selectBoardDetail(params.get("postId"));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("params", params);
		mav.addObject("map", map);
		mav.setViewName("sysMng/boardMng/boardDetail");
		return mav;
	}
	
	// 게시판 등록으로 이동
	@RequestMapping(value = "goReg")
	public ModelAndView goReg(@RequestParam Map<String, String> params) {
		log.info("console - goReg");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("params", params);
		mav.setViewName("sysMng/boardMng/boardReg");
		return mav;
	}
	
	// 게시판 등록
	@RequestMapping(value = "boardReg")
	public ModelAndView boardReg(HttpServletResponse response, @RequestParam Map<String, Object> params) throws IOException {
		log.info("console - boardReg");
		
		int result = boardMngService.insertBoard(params);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		return null;
	}
	
	// 게시판 수정으로 이동
	@RequestMapping(value = "goMod")
	public ModelAndView goMod(@RequestParam Map<String, String> params) {
		log.info("console - goMod");
		
		Map<String, Object> map = boardMngService.selectBoardDetail(params.get("postId"));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("params", params);
		mav.addObject("map", map);
		mav.setViewName("sysMng/boardMng/boardMod");
		return mav;
	}
	
	// 게시판 수정
	@RequestMapping(value = "boardMod")
	public ModelAndView boardMod(HttpServletResponse response, @RequestParam Map<String, Object> params) throws IOException {
		log.info("console - boardMod");
		
		int result = boardMngService.updateBoard(params);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		return null;
	}
	
	// 게시판 삭제
	@RequestMapping(value = "boardDel")
	public ModelAndView boardDel(HttpServletResponse response, @RequestParam Map<String, String> params) throws IOException {
		log.info("console - boardDel");
		
		int result = boardMngService.deleteBoard(params.get("postId"));
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		return null;
	}
}
