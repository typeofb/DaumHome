package com.daumit.sysmng.dao;

import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.web.db.SqlConfig;

@SuppressWarnings("unchecked")
public class CodeMngDao {
	
	private SqlMapClient sql = null;
	
	public CodeMngDao() {
		sql = SqlConfig.getSqlMapInstance();
	}
	
	public List<HashMap<String, Object>> selectAreaList(int targetPage) {
		List<HashMap<String, Object>> result = null;
		try {
			result = sql.queryForList("CodeMng.selectAreaList", targetPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertArea(HashMap<String, Object> iMaps) {
		boolean result = false;
		try {
			sql.update("CodeMng.insertArea", iMaps);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateArea(HashMap<String, Object> iMaps) {
		boolean result = false;
		try {
			sql.update("CodeMng.updateArea", iMaps);
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
