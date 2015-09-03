package com.daumit.sysmng.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.dao.ResultSetData;
import com.daumit.sysmng.dao.BoardMngDao;
import com.daumit.sysmng.dto.BoardMngDto;

@Service("BoardMngService")
public class BoardMngService {
	
	@Resource(name="BoardMngDao")
	private BoardMngDao dao;
	
	public ResultSetData selectBoardList(Map<String, Object> paramMap) {
		ResultSetData result = dao.selectBoardList(paramMap);
		return result;
	}
	
	public int selectBoardCnt(Map<String, Object> paramMap) {
		int result = dao.selectBoardCnt(paramMap);
		return result;
	}
	
	@SuppressWarnings("serial")
	public Map<String, Object> selectBoardDetail(final String postId) {
		Map<String, Object> result = dao.selectBoardDetail(new HashMap<String, Object>(){{put("postId", postId);}});
		return result;
	}
	
	public int insertBoard(Map<String, Object> paramMap) {
		BoardMngDto dto = new BoardMngDto();
		dto.setTitle(paramMap.get("title").toString());
		dto.setContents(paramMap.get("contents").toString());
		int result = dao.insertBoard(dto);
		return result;
	}
	
	public int updateBoard(Map<String, Object> paramMap) {
		BoardMngDto dto = new BoardMngDto();
		dto.setPostId(paramMap.get("postId").toString());
		dto.setTitle(paramMap.get("title").toString());
		dto.setContents(paramMap.get("contents").toString());
		int result = dao.updateBoard(dto);
		return result;
	}
	
	public int deleteBoard(String postId) {
		BoardMngDto dto = new BoardMngDto();
		dto.setPostId(postId);
		int result = dao.deleteBoard(dto);
		return result;
	}
}
