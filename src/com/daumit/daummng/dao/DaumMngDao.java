package com.daumit.daummng.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Repository;

import com.common.dao.QuerySupport;
import com.common.dao.ResultSetData;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.web.db.SqlConfig;

@Repository("DaumMngDao")
public class DaumMngDao extends QuerySupport {
	
//	private SqlMapClient sql = null;
//	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public DaumMngDao() {
//		sql = SqlConfig.getSqlMapInstance();
//		DataSource dataSource = sql.getDataSource();
//		BasicDataSource basicDataSource = (BasicDataSource) dataSource;
//		System.out.println("Active 상태 객체수: " + basicDataSource.getNumActive());
//		System.out.println("Idle 상태 객체수: " + basicDataSource.getNumIdle());
		
//		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ResultSetData selectCopy(List<Object> list) {
		String key = "idx";
//		result = sql.queryForList("DaumMng.selectCopy", list);
		String sql = "SELECT WRITER_ID, WRITER_NAME FROM ARTICLE WHERE 1 = :PARAM AND ARTICLE_SEQ IN (" + createInSql(key, list) + ")";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PARAM", 1);
		assignInParam(key, list, paramMap);
		return queryForResultSet(sql, paramMap);
	}
	
	public ResultSetData selectBBS() {
//		result = sql.queryForList("DaumMng.selectBBS");
		String sql = "SELECT BOARD_ID, BOARD_NAME FROM BOARD WHERE BOARD_NAME = :BOARD_NAME";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("BOARD_NAME", "NOTICE");
//		result = namedParameterJdbcTemplate.queryForList(sql, paramMap);
		return queryForResultSet(sql, paramMap);
	}
	
	public Map<String, Object> selectAuth(Map<String, Object> map) {
//		result = (Map<String, Object>) sql.queryForObject("DaumMng.selectAuth", map);
		String sql = "SELECT CODE, CODE_NAME FROM CODE WHERE USE_YN = :useYN AND SORT_ORDER = :sortOrder";
		return queryForMap(sql, map);
	}
	
	public int selectUser(String string) {
//		result = (Integer) sql.queryForObject("DaumMng.selectUser", string);
		String sql = "SELECT ROLE FROM USER";
		return queryForInt(sql, null);
	}
	
	public boolean insertFile(Map<String, String> pMap) {
		boolean result = false;
//		try {
//			sql.startTransaction();
//			sql.queryForObject("DaumMng.insertFile", pMap);
//			String rowCount = String.valueOf(pMap.get("p_output"));
//			System.out.println("Mysql Function ROW_COUNT() : " + rowCount);
//			sql.getCurrentConnection().commit();
//			sql.endTransaction();
//			result = true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			try {
//				sql.getCurrentConnection().rollback();
//				sql.endTransaction();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
		return result;
	}
}
