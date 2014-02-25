package com.daumit.sysmng.service;

import java.util.HashMap;
import java.util.List;

import com.daumit.sysmng.dao.BoardMngDao;

public class BoardMngService {
	
	private BoardMngDao dao = null;
	
	public BoardMngService() {
		dao = new BoardMngDao();
	}
	
	public List<HashMap<String, Object>> selectBoardList(HashMap<String, Object> iMaps) {
		List<HashMap<String, Object>> result = dao.selectBoardList(iMaps);
		return result;
	}

	public int selectBoardCnt(HashMap<String, Object> iMaps) {
		int result = dao.selectBoardCnt(iMaps);
		return result;
	}

	public HashMap<String, Object> selectBoardDetail(String postId) {
		HashMap<String, Object> result = dao.selectBoardDetail(postId);
		return result;
	}

	public boolean insertBoard(HashMap<String, Object> iMaps) {
		boolean result = dao.insertBoard(iMaps);
		return result;
	}
	
	public boolean updateBoard(HashMap<String, Object> iMaps) {
		boolean result = dao.updateBoard(iMaps);
		return result;
	}

	public boolean deleteBoard(String postId) {
		boolean result = dao.deleteBoard(postId);
		return result;
	}
}
