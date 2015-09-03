package com.common.dao;

import java.util.List;
import java.util.Map;

import com.common.dao.support.BaseJdbcDaoSupport;

public class QuerySupport extends BaseJdbcDaoSupport {

	protected List<Map<String, Object>> queryForPage(String sql, Map<String, Object> paramMap, int expectedRows) {
		return super.queryForPage(sql, paramMap, expectedRows);
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
