package com.common.dao.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public interface BaseNamedParameterJdbcOperation extends NamedParameterJdbcOperations {

	public abstract List<Map<String, Object>> queryForPage(String sql, Map<String, ?> paramMap, int expectedRows);
}
