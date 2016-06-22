package com.common.tags;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONTag extends TagBase {

	private static final long serialVersionUID = 1L;
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	public int doStartTag() {
		try {
			this.value = getValue();
			if (value == null || value.equals("")) {
				throw new JspTagException("invalid null or empty 'value'");
			}
			
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			
			JsonFactory factory = new JsonFactory();
			JsonParser parser = factory.createJsonParser(value);
			JsonToken token = parser.nextToken();
			ObjectMapper mapper = new ObjectMapper();
			if (token.equals(JsonToken.START_ARRAY)) {
				ArrayList<HashMap<String, Object>> readValue = mapper.readValue(value, ArrayList.class);
				for (int i = 0; i < readValue.size(); i++) {
					HashMap<String, Object> map = readValue.get(i);
					sb.append("<a href='" + ((HttpServletRequest) (pageContext.getRequest())).getContextPath() + map.get("fakeNm") + "'>");
					sb.append(map.get("realNm"));
					sb.append("</a>");
				}
				sb.append("<br>");
			} else if (token.equals(JsonToken.START_OBJECT)) {
				HashMap<String, Object> readValue = mapper.readValue(value, HashMap.class);
				sb.append("<a href='" + ((HttpServletRequest) (pageContext.getRequest())).getContextPath() + readValue.get("fakeNm") + "'>");
				sb.append(readValue.get("realNm"));
				sb.append("</a>");
				sb.append("<br>");
			}
			out.write(sb.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return SKIP_BODY;
	}
}
