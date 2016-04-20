package com.daumit.sysmng.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.dao.ResultSetData;
import com.common.dao.SearchCondition;
import com.daumit.sysmng.dao.BoardMngDao;
import com.daumit.sysmng.dto.BoardMngDto;

@Service("BoardMngService")
public class BoardMngService {
	
	@Resource(name="BoardMngDao")
	private BoardMngDao dao;
	
	public ResultSetData selectBoardList(Map<String, Object> paramMap, SearchCondition sc) {
		ResultSetData result = dao.selectBoardList(paramMap, sc);
		return result;
	}
	
//	public int selectBoardCnt(Map<String, Object> paramMap) {
//		int result = dao.selectBoardCnt(paramMap);
//		return result;
//	}
	
	@SuppressWarnings("serial")
	public Map<String, Object> selectBoardDetail(final String postId) {
		Map<String, Object> result = dao.selectBoardDetail(new HashMap<String, Object>(){{put("postId", postId);}});
		return result;
	}
	
	public int insertBoard(BoardMngDto boardMngDto) {
		int result = dao.insertBoard(boardMngDto);
		return result;
	}
	
	public int updateBoard(BoardMngDto boardMngDto) {
		int result = dao.updateBoard(boardMngDto);
		return result;
	}
	
	public int deleteBoard(BoardMngDto boardMngDto) {
		int result = dao.deleteBoard(boardMngDto);
		return result;
	}
}
