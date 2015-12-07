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
	
	public List<Map<String, Object>> selectCodeList(Map<String, Object> paramMap) {
		List<Map<String, Object>> result = null;
		try {
			result = sql.queryForList("CodeMng.selectCodeList", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertCode(Map<String, Object> paramMap) {
		boolean result = false;
		try {
			sql.update("CodeMng.insertCode", paramMap);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateCode(Map<String, Object> paramMap) {
		boolean result = false;
		try {
			sql.update("CodeMng.updateCode", paramMap);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteCode(Map<String, Object> paramMap) {
		boolean result = false;
		try {
			sql.delete("CodeMng.deleteCode", paramMap);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
