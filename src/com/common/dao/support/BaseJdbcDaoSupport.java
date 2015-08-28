package com.common.dao.support;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.common.dao.jdbc.BaseNamedParameterJdbcOperation;
import com.common.dao.jdbc.BaseNamedParameterJdbcTemplate;


public class BaseJdbcDaoSupport extends AbstractDaoOperationSupport implements InitializingBean {

	private BaseNamedParameterJdbcOperation namedParameterJdbcTemplate;
	
	public BaseJdbcDaoSupport() {
		
	}
	
	public BaseJdbcDaoSupport(DataSource dataSource) {
		initTemplate(dataSource);
	}
	
	public BaseJdbcDaoSupport(JdbcTemplate jdbcTemplate) {
		namedParameterJdbcTemplate = new BaseNamedParameterJdbcTemplate(jdbcTemplate);
	}
	
	@Override
	protected void initTemplate(DataSource dataSource) {
		namedParameterJdbcTemplate = new BaseNamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	protected boolean isInitialized() {
		return namedParameterJdbcTemplate == null;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(namedParameterJdbcTemplate, "BaseJdbcDaoSupport\uC758 JdbcTemplate\uC740 Null\uC774 \uC62C \uC218 \uC5C6\uC2B5\uB2C8\uB2E4.");
	}
	
	protected List<Map<String, Object>> queryForPage(String sql, Map<String, ?> paramMap, int expectedRows) {
		return namedParameterJdbcTemplate.queryForPage(sql, paramMap, expectedRows);
	}
	
	protected List<Map<String, Object>> queryForList(String sql, Map<String, ?> paramMap) {
		return namedParameterJdbcTemplate.queryForList(sql, paramMap);
	}
}
