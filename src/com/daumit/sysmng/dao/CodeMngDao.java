package com.daumit.sysmng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.web.db.SqlConfig;

@SuppressWarnings("unchecked")
@Repository("CodeMngDao")
public class CodeMngDao {
	
	private SqlMapClient sql = null;
	
	public CodeMngDao() {
		sql = SqlConfig.getSqlMapInstance();
	}
	
	public List<Map<String, Object>> selectAreaList(int targetPage) {
		List<Map<String, Object>> result = null;
		try {
			result = sql.queryForList("CodeMng.selectAreaList", targetPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertArea(Map<String, Object> paramMap) {
		boolean result = false;
		try {
			sql.update("CodeMng.insertArea", paramMap);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateArea(Map<String, Object> paramMap) {
		boolean result = false;
		try {
			sql.update("CodeMng.updateArea", paramMap);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteArea(String postId) {
		boolean result = false;
		try {
			sql.delete("CodeMng.deleteArea", postId);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
