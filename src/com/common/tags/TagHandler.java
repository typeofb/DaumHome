package com.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.common.dao.ResultSetData;
import com.common.dao.SearchCondition;

public class TagHandler extends TagBase {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String text;
	private String rscKey;
	private String field;
	private String headerSortYn;
	private String headerSortField;
	private String headerSortOrderBy;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getRscKey() {
		return rscKey;
	}
	
	public void setRscKey(String rscKey) {
		this.rscKey = rscKey;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
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
	
	@Override
	public int doEndTag() throws JspException {
		if (this.getName() != null) {
			Object obj = getParameter(this.getName());
			if (obj != null && obj instanceof ResultSetData) {
				ResultSetData resultSetData = (ResultSetData) obj;
				this.setHeaderSortYn(resultSetData.getHeaderSortYn());
				this.setHeaderSortField(resultSetData.getHeaderSortField());
				this.setHeaderSortOrderBy(resultSetData.getHeaderSortOrderBy());
			} else {
				Object sc = getParameter("searchCondition");
				SearchCondition searchCondition = (SearchCondition) sc;
				this.setHeaderSortYn(searchCondition.getHeaderSortYn());
				this.setHeaderSortField(searchCondition.getHeaderSortField());
				this.setHeaderSortOrderBy(searchCondition.getHeaderSortOrderBy());
			}
		}
		
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		if ("Y".equals(this.headerSortYn)) {
			if (field.equals(this.headerSortField)) {
				if ("ASC".equals(this.headerSortOrderBy)) {
					sb.append("<span style=\"cursor:pointer;\" onclick=\"headerSort('" + this.field + "', 'DESC');\">" + this.text + "&nbsp;▲</span>");
				} else if ("DESC".equals(this.headerSortOrderBy)) {
					sb.append("<span style=\"cursor:pointer;\" onclick=\"headerSort('" + this.field + "', 'ASC');\">" + this.text + "&nbsp;▼</span>");
				}
			} else {
				sb.append("<span style=\"cursor:pointer;\" onclick=\"headerSort('" + this.field + "', 'ASC');\">" + this.text + "&nbsp;↕</span>");
			}
		} else {
			sb.append("<span style=\"cursor:pointer;\" onclick=\"headerSort('" + this.field + "', 'ASC');\">" + this.text + "&nbsp;↕</span>");
		}
		try {
			out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return EVAL_PAGE;
	}
}
