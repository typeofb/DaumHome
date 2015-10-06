package com.common.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
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
	
	protected ResultSetData queryForPage(String sql, Map<String, Object> paramMap, SearchCondition sc) {
		StringBuffer originalSql = new StringBuffer(sql);
		StringBuffer conditionSql = new StringBuffer();
		
		Map<String, Object> searchParams = sc.getSearchParam();
		for (Iterator<Object> keys = sc.getSearchKey().iterator(); keys.hasNext();) {
			String key = (String) keys.next();
			SearchCondition.SearchParam searchParam = (SearchCondition.SearchParam) searchParams.get(key);
			if (searchParam == null)
				continue;
			String value = searchParam.getValue();
			// 검색어
			if (searchParam.isStringType()) {
				if (value != null && !value.equalsIgnoreCase("")) {
					String param1 = "PKEY1";
					conditionSql.append(" AND UPPER(" + searchParam.getColumn() + ") LIKE UPPER(:" + param1 + ")");
					paramMap.put(param1, "%" + value + "%");
				}
			}
			// 검색날짜
			if (searchParam.isDateType()) {
				if (value != null && !value.equalsIgnoreCase("")) {
					String param2 = "PKEY2";
					String param3 = "PKEY3";
					conditionSql.append(" AND " + searchParam.getColumn() + " BETWEEN :" + param2 + " AND :" + param3 + "");
					String[] arrValue = value.split("~");
					Timestamp beginDate = getSQLTimestamp(arrValue[0] + " 00:00:00");
					Timestamp endDate = getSQLTimestamp(arrValue[1] + " 23:59:59");
					paramMap.put(param2, beginDate);
					paramMap.put(param3, endDate);
				}
			}
		}
		// 검색순서
		if (sc.hasOrder()) {
			for (Iterator<Object> keys = sc.getOrder().iterator(); keys.hasNext();) {
				SearchCondition.Order key = (SearchCondition.Order) keys.next();
				if (key.getFieldName() != null && !key.getFieldName().equalsIgnoreCase(""))
					conditionSql.append(" ORDER BY " + key.getFieldName() + " " + (key.isAscending() ? "ASC" : "DESC"));
			}
		}
		originalSql.append(conditionSql);
		
		long startPosition = (int) sc.getRowSize() * ((int) sc.getTargetPage() - 1) + 1;
		long endPosition = startPosition + (int) sc.getRowSize();
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
		ResultSetData rs = new ResultSetData(resultList);
		rs.setHeaderSortYn(sc.getHeaderSortYn());
		rs.setHeaderSortField(sc.getHeaderSortField());
		rs.setHeaderSortOrderBy(sc.getHeaderSortOrderBy());
		return rs;
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
