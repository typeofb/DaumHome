package com.common.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.common.dao.support.BaseJdbcDaoSupport;

public class QuerySupport extends BaseJdbcDaoSupport {

	protected List<Map<String, Object>> queryForPage(String sql, Map<String, Object> paramMap, int expectedRows) {
		return super.queryForPage(sql, paramMap, expectedRows);
	}
	
	private Timestamp getSQLTimestamp(String date) {
		java.sql.Timestamp sDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss", Locale.KOREA);
		try {
			sDate = new Timestamp(sdf.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sDate;
	}
	
	protected ResultSetData queryForPage(String sql, Map<String, Object> paramMap) {
		StringBuffer originalSql = new StringBuffer(sql);
		StringBuffer conditionSql = new StringBuffer();
		// 검색어
		if (paramMap.get("searchText") != null && !paramMap.get("searchText").toString().equalsIgnoreCase("")) {
			String param1 = "PKEY1";
			conditionSql.append(" AND UPPER(" + paramMap.get("selectItem") + ") LIKE UPPER(:" + param1 + ")");
			paramMap.put(param1, "%" + paramMap.get("searchText") + "%");
		}
		// 검색날짜
		if (paramMap.get("beginDate") != null && !paramMap.get("beginDate").toString().equalsIgnoreCase("")) {
			String param2 = "PKEY2";
			String param3 = "PKEY3";
			conditionSql.append(" AND " + paramMap.get("orderBy") + " BETWEEN :" + param2 + " AND :" + param3 + "");
			Timestamp beginDate = getSQLTimestamp(paramMap.get("beginDate").toString() + " 00:00:00");
			Timestamp endDate = getSQLTimestamp(paramMap.get("endDate").toString() + " 23:59:59");
			paramMap.put(param2, beginDate);
			paramMap.put(param3, endDate);
		}
		// 검색순서
		if (paramMap.get("orderBy") != null && !paramMap.get("orderBy").toString().equalsIgnoreCase(""))
			conditionSql.append(" ORDER BY " + paramMap.get("orderBy") + " DESC");
		originalSql.append(conditionSql);
		
		long startPosition = (int) paramMap.get("rowSize") * ((int) paramMap.get("targetPage") - 1) + 1;
		long endPosition = startPosition + (int) paramMap.get("rowSize");
		StringBuffer row = new StringBuffer();
		row.append("SELECT a.H_CNT - a.H_ROW + 1 AS H_SEQ, a.*");
		row.append(" FROM (");
		row.append("SELECT ROWNUM AS H_ROW, COUNT(1) OVER() AS H_CNT, H_LIST.*");
		row.append(" FROM (");
		row.append(originalSql);
		row.append(") H_LIST");
		row.append(") a");
		row.append(" WHERE H_ROW >= " + startPosition);
		row.append(" AND H_ROW < " + endPosition);
		sql = row.toString();
		
		List<Map<String, Object>> resultList = queryForList(sql, paramMap);
		if (resultList == null || resultList.size() == 0)
			return new ResultSetData(0);
		return new ResultSetData(resultList);
	}
	
	protected ResultSetData queryForResultSet(String sql, Map<String, Object> paramMap) {
		List<Map<String, Object>> resultList = queryForList(sql, paramMap);
		if (resultList == null || resultList.size() == 0)
			return new ResultSetData(0);
		return new ResultSetData(resultList);
	}
	
	protected Map<String, Object> queryForMap(String sql, Map<String, Object> paramMap) {
		List<Map<String, Object>> resultList = queryForList(sql, paramMap);
		if (resultList == null || resultList.size() == 0)
			return null;
		return (Map<String, Object>) resultList.get(0);
	}
	
	protected String queryForString(String sql, Map<String, Object> paramMap) {
		List<Map<String, Object>> resultList = queryForList(sql, paramMap);
		if (resultList == null || resultList.size() == 0)
			return null;
		Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
		Map.Entry<String, Object> entry = (Map.Entry<String, Object>) resultMap.entrySet().iterator().next();
		return (entry.getValue() != null) ? entry.getValue().toString() : null;
	}
	
	protected int queryForInt(String sql, Map<String, Object> paramMap) {
		String value = queryForString(sql, paramMap);
		return (value != null) ? Integer.parseInt(value) : 0;
	}
	
	public String createInSql(String key, List<Object> list) {
		StringBuffer sqlBuffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sqlBuffer.append(":").append(key).append("_").append(i + 1);
			if (i < list.size() - 1)
				sqlBuffer.append(", ");
		}
		return sqlBuffer.toString();
	}
	
	public String createInSql(String key, String[] str) {
		StringBuffer sqlBuffer = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			sqlBuffer.append(":").append(key).append("_").append(i + 1);
			if (i < str.length - 1)
				sqlBuffer.append(", ");
		}
		return sqlBuffer.toString();
	}
	
	public void assignInParam(String key, List<Object> list, Map<String, Object> paramMap) {
		for (int i = 0; i < list.size(); i++)
			paramMap.put(key + "_" + (i + 1), list.get(i).toString());
	}
	
	public void assignInParam(String key, String[] str, Map<String, Object> paramMap) {
		for (int i = 0; i < str.length; i++) {
			paramMap.put(key + "_" + (i + 1), str[i]);
		}
	}
}
