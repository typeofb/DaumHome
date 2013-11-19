package com.daumit.daummng.dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.web.db.SqlConfig;

@SuppressWarnings("unchecked")
public class DaumMngDao {

	private SqlMapClient sql = SqlConfig.getSqlMapInstance();

	public List<Map<String, Object>> selectBBS(Map<String, Object> map) {
		List<Map<String, Object>> result = null;
		try {
			result = sql.queryForList("DaumMng.selectBBS", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, Object> selectAuth(Map<String, Object> map) {
		Map<String, Object> result = null;
		try {
			result = (Map<String, Object>) sql.queryForObject("DaumMng.selectAuth", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int selectUser(String string) {
		int result = 0;
		try {
			result = (Integer) sql.queryForObject("DaumMng.selectUser", string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
