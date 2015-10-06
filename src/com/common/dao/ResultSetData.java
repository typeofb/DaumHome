package com.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DB 질의 결과를 담고 있는 클래스
 * 
 * 1) List 형태지만 ResultSet에서 유용하게 사용하던 next(), previous() 같은 함수 지원
 * 2) 단일 질의 결과시 next() 없이 get(String)을 통해 바로 객체 사용 가능
 * 
 * @author : 신현호
 */
public class ResultSetData extends ArrayList<Object> implements java.io.Serializable, java.util.List<Object> {

	private static final long serialVersionUID = 6166364603506766931L;
	
	private int cursor = -1;
	
	private boolean multiRow;
	private boolean multiObject;
	
	private List<Object> orginal;
	
	private String headerSortYn;
	private String headerSortField;
	private String headerSortOrderBy;
	
	public List<Object> getOrginal() {
		return orginal;
	}
	
	public void setOrginal(List<Object> orginal) {
		this.orginal = orginal;
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
	
	public ResultSetData(List<Map<String, Object>> list) {
		super(list);
	}
	
	public ResultSetData(int size) {
		super(size);
	}
	
	public Object get() {
		if (size() == 0)
			return null;
		if (cursor == -1)
			return get(0);
		else
			return get(cursor);
	}
	
	public Object get(String key) {
		Object obj = get();
		if (obj instanceof Map<?, ?>) {
			Map<?, ?> row = (Map<?, ?>) get();
			return row.get(key);
		} else {
			Map<?, ?> originalRow = (Map<?, ?>) orginal.get(cursor);
			return originalRow.get(key);
		}
	}
	
	public String getString(String key) {
		if (get(key) == null)
			return null;
		return get(key).toString();
	}
	
	public int getInt(String key) {
		String value = getString(key);
		if (value == null)
			return 0;
		return Integer.parseInt(getString(key));
	}
	
	public long getLong(String key) {
		String value = getString(key);
		if (value == null)
			return 0;
		return Long.parseLong(getString(key));
	}
	
	public boolean getBoolean(String key) {
		String value = getString(key);
		if (value == null)
			return false;
		return (value.equalsIgnoreCase("y") ||
				value.equalsIgnoreCase("yes") ||
				value.equalsIgnoreCase("on") ||
				value.equalsIgnoreCase("1") ||
				value.equalsIgnoreCase("true"));
	}
	
	public int getRow() {
		return cursor;
	}
	
	public void beforeFirst() {
		cursor = -1;
	}
	
	public void first() {
		cursor = 0;
	}
	
	public void last() {
		cursor = size();
	}
	
	public void afterLast() {
		cursor = size() + 1;
	}
	
	public boolean previous() {
		if (cursor > -1) {
			cursor--;
			return true;
		} else
			return false;
	}
	
	public boolean next() {
		if (cursor + 1 >= size())
			return false;
		else {
			cursor++;
			return true;
		}
	}
	
	public boolean isFirst() {
		return cursor == 0;
	}
	
	public boolean isLast() {
		return cursor == size();
	}
	
	public boolean isMultiRow() {
		return multiRow;
	}
	
	public boolean isMultiObject() {
		return multiObject;
	}
}
