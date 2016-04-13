package com.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.common.servlet.ServletUtils;

public class FileUploadTag extends TagBase {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer source = new StringBuffer();
		try {
			source.append(ServletUtils.include("globalError.jsp", pageContext));
			source.append("<script language=\"javascript\">\n");
			source.append("alert(\"FileUploadTag1\");\n");
			source.append("</script>\n");
			
			setTagValue("initializedInnoFD", "Y", false);
			
			pageContext.setAttribute("fileUpload", this, PageContext.REQUEST_SCOPE);
			
			addScript("<script language=\"javascript\">\nalert(\"FileUploadTag2\");\n</script>\n");
			
			out.print(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
