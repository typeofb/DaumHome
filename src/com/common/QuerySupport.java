package com.common;

import java.util.List;
import java.util.Map;

import com.common.dao.support.BaseJdbcDaoSupport;

public class QuerySupport extends BaseJdbcDaoSupport {

	protected List<Map<String, Object>> queryForPage(String sql, Map<String, ?> paramMap, int expectedRows) {
		return super.queryForPage(sql, paramMap, expectedRows);
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
