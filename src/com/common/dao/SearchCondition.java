package com.common.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCondition extends HashMap<String, Object> {

	private static final long serialVersionUID = -3477619336462002875L;
	
	private int rowSize = 10;
	private int targetPage = 1;
	private int pageGroupSize = 10;
	private List<Object> order;
	private List<Object> searchKey;
	private Map<String, Object> searchParam;
	private Map<String, Object> requestData;
	
	private String headerSortYn;
	private String headerSortField;
	private String headerSortOrderBy;
	
	public SearchCondition() {
		super();
	}
	
	public SearchCondition(Map<String, Object> requestData) {
		this.requestData = requestData;
	}
	
	public int getRowSize() {
		return rowSize;
	}
	
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}
	
	public int getTargetPage() {
		return targetPage;
	}
	
	public void setTargetPage(int targetPage) {
		this.targetPage = targetPage;
	}
	
	public int getPageGroupSize() {
		return pageGroupSize;
	}
	
	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}
	
	public List<Object> getOrder() {
		return order;
	}
	
	public void addOrder(String fieldName, boolean ascending) {
		if (order == null)
			order = new ArrayList<Object>();
		order.add(new Order(fieldName, ascending));
	}
	
	public boolean hasOrder() {
		return order != null && order.size() > 0;
	}
	
	public void clearOrder() {
		this.order = null;
	}
	
	public List<Object> getSearchKey() {
		return searchKey;
	}
	
	public void addSearchKey(String key) {
		if (searchKey == null)
			searchKey = new ArrayList<Object>();
		searchKey.add(key);
	}
	
	public boolean hasSearchKey() {
		return searchKey != null && searchKey.size() > 0;
	}
	
	public Map<String, Object> getSearchParam() {
		return searchParam;
	}
	
	public void addSearchParam(String key, String column, String value, String type, String operator) {
		if (searchParam == null)
			searchParam = new HashMap<String, Object>();
		searchParam.put(key, new SearchParam(key, column, value, type, operator));
	}
	
	public Map<String, Object> getRequestData() {
		return requestData;
	}
	
	public void setRequestData(HashMap<String, Object> requestData) {
		this.requestData = requestData;
	}
	
	public String getHeaderSortYn() {
		return headerSortYn;
	}
	
	public void setHeaderSortYn(String headerSortYn) {
		this.headerSortYn = headerSortYn;
	}
	
	public String getHeaderSortField() {
		return headerSortField;
	}
	
	public void setHeaderSortField(String headerSortField) {
		this.headerSortField = headerSortField;
	}
	
	public String getHeaderSortOrderBy() {
		return headerSortOrderBy;
	}
	
	public void setHeaderSortOrderBy(String headerSortOrderBy) {
		this.headerSortOrderBy = headerSortOrderBy;
	}
	
	public class SearchParam {
		
		private String key;
		private String column;
		private String value;
		private String type;
		private String operator;
		
		public SearchParam(String key, String column, String value, String type, String operator) {
			this.key = key;
			this.column = column;
			this.value = value;
			this.type = type;
			this.operator = operator;
		}
		
		public String getKey() {
			return key;
		}
		
		public String getColumn() {
			return column;
		}
		
		public String getValue() {
			return value;
		}
		
		public String getType() {
			return type;
		}
		
		public String getOperator() {
			return operator;
		}
		
		public boolean isDateType() {
			return "date".equalsIgnoreCase(type);
		}
		
		public boolean isStringType() {
			return "string".equalsIgnoreCase(type);
		}
		
		public boolean isIntType() {
			return "int".equalsIgnoreCase(type) || "integer".equalsIgnoreCase(type);
		}
		
		public boolean isLikeOperator() {
			return "like".equalsIgnoreCase(operator);
		}
		
		public boolean isEqualOperator() {
			return "eq".equalsIgnoreCase(operator);
		}
		
		public boolean isBetweenOperator() {
			return "between".equalsIgnoreCase(operator);
		}
		
		public boolean isLessThanOperator() {
			return "lt".equalsIgnoreCase(operator);
		}
		
		public boolean isGreaterThanOperator() {
			return "gt".equalsIgnoreCase(operator);
		}
		
		public boolean isLessThanOrEqualOperator() {
			return "lteq".equalsIgnoreCase(operator);
		}
		
		public boolean isGreaterThanOrEqualOperator() {
			return "gteq".equalsIgnoreCase(operator);
		}
		
		public boolean isNullOperator() {
			return "null".equalsIgnoreCase(operator);
		}
		
		public boolean isNotNullOperator() {
			return "notnull".equalsIgnoreCase(operator);
		}
		
		public boolean isQuarterOperator() {
			return "quarter".equalsIgnoreCase(operator);
		}
		
		public boolean isRuleOperator() {
			return "rule".equalsIgnoreCase(operator);
		}
	}
	
	public class Order {
		
		private String fieldName;
		private boolean ascending;
		
		public Order(String fieldName, boolean ascending) {
			this.fieldName = fieldName;
			this.ascending = ascending;
		}
		
		public String getFieldName() {
			return fieldName;
		}
		
		public boolean isAscending() {
			return ascending;
		}
	}
}
