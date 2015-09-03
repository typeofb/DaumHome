package com.common.dao.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class BaseNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate implements BaseNamedParameterJdbcOperation {

	public BaseNamedParameterJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	public BaseNamedParameterJdbcTemplate(JdbcOperations classicJdbcTemplate) {
		super(classicJdbcTemplate);
	}
	
	private void checkParameterObject(Object paramObj) throws IllegalArgumentException {
		if (paramObj == null)
			throw new IllegalArgumentException("\uD30C\uB77C\uBBF8\uD130 \uAC1D\uCCB4\uB294 Null\uC774 \uB420 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4.");
		else
			return;
	}
	
	@Override
	public List<Map<String, Object>> queryForPage(String sql, Map<String, Object> paramMap, int expectedRows) {
		List<Map<String, Object>> result = queryForList(sql, paramMap);
		if (result != null && expectedRows != result.size())
			throw new IncorrectResultSizeDataAccessException(expectedRows, result.size());
		else
			return result;
	}
	
	@Override
	public int update(String sql, Object paramObj) {
		checkParameterObject(paramObj);
		return update(sql, ((SqlParameterSource) (new BeanPropertySqlParameterSource(paramObj))));
	}
}
