package com.daumit.sysmng.dao;

import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.web.db.SqlConfig;

@SuppressWarnings("unchecked")
public class BoardMngDao {
	
	private SqlMapClient sql = null;
	
	public BoardMngDao() {
		sql = SqlConfig.getSqlMapInstance();
	}
	
	public List<HashMap<String, Object>> selectBoardList(HashMap<String, Object> iMaps) {
		List<HashMap<String, Object>> result = null;
		try {
			result = sql.queryForList("BoardMng.selectBoardList", iMaps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int selectBoardCnt(HashMap<String, Object> iMaps) {
		int result = 0;
		try {
			result = (Integer) sql.queryForObject("BoardMng.selectBoardCnt", iMaps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public HashMap<String, Object> selectBoardDetail(String postId) {
		HashMap<String, Object> result = null;
		try {
			result = (HashMap<String, Object>) sql.queryForObject("BoardMng.selectBoardDetail", postId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertBoard(HashMap<String, Object> iMaps) {
		boolean result = false;
		try {
			sql.update("BoardMng.insertBoard", iMaps);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateBoard(HashMap<String, Object> iMaps) {
		boolean result = false;
		try {
			sql.update("BoardMng.updateBoard", iMaps);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteBoard(String postId) {
		boolean result = false;
		try {
			sql.delete("BoardMng.deleteBoard", postId);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
