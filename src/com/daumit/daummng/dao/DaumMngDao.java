package com.daumit.daummng.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.web.db.SqlConfig;

@SuppressWarnings("unchecked")
public class DaumMngDao {

	private SqlMapClient sql = SqlConfig.getSqlMapInstance();

	public List<Map<String, Object>> selectBBS(Map<String, Object> map) {
		DataSource dataSource = sql.getDataSource();
		BasicDataSource basicDataSource = (BasicDataSource) dataSource;
		System.out.println("Active 상태 객체수: " + basicDataSource.getNumActive());
		System.out.println("Idle 상태 객체수: " + basicDataSource.getNumIdle());

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

	public boolean insertFile(HashMap<String, String> pMap) {
		boolean result = false;
		try {
			sql.startTransaction();
			sql.queryForObject("DaumMng.insertFile", pMap);
			String rowCount = String.valueOf(pMap.get("p_output"));
			System.out.println("Mysql Function ROW_COUNT() : " + rowCount);
			sql.getCurrentConnection().commit();
			sql.endTransaction();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				sql.getCurrentConnection().rollback();
				sql.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
}
