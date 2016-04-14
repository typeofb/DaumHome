package com.common.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public class InitTag extends TagBase {

	private static final long serialVersionUID = 1L;
	
	public int doStartTag() throws JspException {
		StringBuffer sb = (StringBuffer) getTagValue("JS", false);
		JspWriter out = pageContext.getOut();
		try {
			if (sb != null)
				out.print(sb.toString());
			List incJSList = (List) getTagValue("resources.js", false);
			if (incJSList != null) {
				if (getTagValue("resources.js.size", false) != null) {
					int previousIncSize = ((Integer) getTagValue("resources.js.size", false)).intValue();
					for (int i = 0; i < previousIncSize; i++) {
						if (incJSList.size() > 0)
							incJSList.remove(0);
					}
				}
				if (incJSList.size() > 0) {
					out.println();
					out.print("jQuery.resources.add('js', '");
					int i = 0;
					for (Iterator incJSIter = incJSList.iterator(); incJSIter.hasNext(); i++) {
						if (i > 0)
							out.print(",");
						out.print(incJSIter.next());
					}
					out.println("');");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
