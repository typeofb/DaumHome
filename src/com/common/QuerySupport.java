package com.common;

import java.util.List;
import java.util.Map;

import com.common.dao.support.BaseJdbcDaoSupport;

public class QuerySupport extends BaseJdbcDaoSupport {

	protected List<Map<String, Object>> queryForPage(String sql, Map<String, ?> paramMap, int expectedRows) {
		return super.queryForPage(sql, paramMap, expectedRows);
	};
}
