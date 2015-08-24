package com.common.dao.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BaseNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate implements BaseNamedParameterJdbcOperation {

	public BaseNamedParameterJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	public BaseNamedParameterJdbcTemplate(JdbcOperations classicJdbcTemplate) {
		super(classicJdbcTemplate);
	}
	
	@Override
	public List<Map<String, Object>> queryForPage(String sql, Map<String, ?> paramMap, int expectedRows) {
		List<Map<String, Object>> result = queryForList(sql, paramMap);
		if (result != null && expectedRows != result.size())
			throw new IncorrectResultSizeDataAccessException(expectedRows, result.size());
		else
			return result;
	}
}
