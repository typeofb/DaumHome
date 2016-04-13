package com.common.tags;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class TagBase extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private static final String TAG_CONTEXT = "TAGCTX";
	
	public Object getParameter(String name) throws JspTagException {
		Object beanValue = pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
		if (beanValue == null)
			beanValue = pageContext.getRequest().getAttribute(name);
		if (beanValue == null)
			beanValue = pageContext.getRequest().getParameter(name);
		return beanValue;
	}
	
	public Object getParameterValues(String name) throws JspTagException {
		Object beanValue = pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
		if (beanValue == null)
			beanValue = pageContext.getRequest().getAttribute(name);
		if (beanValue == null)
			beanValue = pageContext.getRequest().getParameterValues(name);
		return beanValue;
	}
	
	public void setParameter(String name, Object value) {
		pageContext.getRequest().setAttribute(name, value);
	}
	
	public Object getTagValue(String key) {
		return getTagValue(key, true);
	}
	
	public void setTagValue(String key, Object value) {
		setTagValue(key, value, true);
	}
	
	public Object getTagValue(String key, boolean isLocal) {
		Map tagCtx = (HashMap) pageContext.getAttribute(TAG_CONTEXT, PageContext.REQUEST_SCOPE);
		if (tagCtx == null)
			return null;
		return tagCtx.get(isLocal ? getTagClassName() + key : key);
	}
	
	public void setTagValue(String key, Object value, boolean isLocal) {
		Map tagCtx = (HashMap) pageContext.getAttribute(TAG_CONTEXT, PageContext.REQUEST_SCOPE);
		if (tagCtx == null) {
			tagCtx = new HashMap();
			pageContext.setAttribute(TAG_CONTEXT, tagCtx, PageContext.REQUEST_SCOPE);
		}
		tagCtx.put(isLocal ? getTagClassName() + key : key, value);
	}
	
	public String getTagClassName() {
		return this.getClass().getName() + "_";
	}
	
	public void addScript(String script) {
		addScript(new StringBuffer(script));
	}
	
	public void addScript(StringBuffer script) {
		StringBuffer sb = (StringBuffer) getTagValue("JS", false);
		if (sb == null) {
			setTagValue("JS", script, false);
		} else {
			sb.append("\r\n");
			sb.append(script);
		}
	}
	
	public String getContext() {
		return ((HttpServletRequest) (pageContext.getRequest())).getContextPath();
	}
}
