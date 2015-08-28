package com.daumit.daummng.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.common.dao.QuerySupport;
import com.common.dao.ResultSetData;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.web.db.SqlConfig;

@Repository("DaumMngDao")
public class DaumMngDao extends QuerySupport {
	
	private SqlMapClient sql = null;
//	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public DaumMngDao() {
		sql = SqlConfig.getSqlMapInstance();
		DataSource dataSource = sql.getDataSource();
		BasicDataSource basicDataSource = (BasicDataSource) dataSource;
		System.out.println("Active 상태 객체수: " + basicDataSource.getNumActive());
		System.out.println("Idle 상태 객체수: " + basicDataSource.getNumIdle());
		
//		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Map<String, Object>> selectCopy(List<Object> list) {
		List<Map<String, Object>> result = null;
		String key = "idx";
		try {
//			result = sql.queryForList("DaumMng.selectCopy", list);
			String sql = "SELECT WRITER_ID, WRITER_NAME FROM ARTICLE WHERE 1 = :PARAM AND ARTICLE_SEQ IN (" + createInSql(key, list) + ")";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("PARAM", 1);
			assignInParam(key, list, paramMap);
			result = queryForPage(sql, paramMap, 11);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Map<String, Object>> selectBBS() {
		List<Map<String, Object>> result = null;
		try {
//			result = sql.queryForList("DaumMng.selectBBS");
			String sql = "SELECT BOARD_ID, BOARD_NAME FROM BOARD WHERE BOARD_NAME = :BOARD_NAME";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("BOARD_NAME", "NOTICE");
//			result = namedParameterJdbcTemplate.queryForList(sql, paramMap);
			result = queryForPage(sql, paramMap, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSetData selectAuth(Map<String, Object> map) {
		ResultSetData result = null;
		try {
//			result = (Map<String, Object>) sql.queryForObject("DaumMng.selectAuth", map);
			String sql = "SELECT CODE, CODE_NAME FROM CODE WHERE USE_YN = :useYN AND SORT_ORDER = :sortOrder";
			result = queryForResultSet(sql, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSetData selectUser(String string) {
		ResultSetData result = null;
		try {
//			result = (Integer) sql.queryForObject("DaumMng.selectUser", string);
			String sql = "SELECT ROLE FROM USER";
			result = queryForResultSet(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertFile(Map<String, String> pMap) {
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
