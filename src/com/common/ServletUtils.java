package com.common;

import java.util.Enumeration;

import com.common.BufferedResponseWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ServletUtils {

	/**
	 * JSP include 메커니즘과 동일하나 결과를 화면에 뿌리지 않고 메모리에 담아서 String 값으로 리턴 Controller에서 사용
	 * @param jsp include할 JSP
	 * @param request
	 * @param response
	 * @return include 실행 결과 값
	 * @throws Exception
	 */
	public static String include(String jsp, ServletRequest request, ServletResponse response) throws Exception {
		BufferedResponseWrapper bufferedResponse = new BufferedResponseWrapper((HttpServletResponse) response);
		return include(jsp, request, response, bufferedResponse);
	}
	
	public static String include(String jsp, ServletRequest request, ServletResponse response, BufferedResponseWrapper bufferedResponse) throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		response.setContentType("text/html; charset=UTF-8");
		dispatcher.include(request, bufferedResponse);
		response.flushBuffer();
		if (bufferedResponse.getException() != null)
			throw bufferedResponse.getException();
		return bufferedResponse.toString();
	}
	
	public static String include(String jsp, PageContext pageContext) throws Exception {
		BufferedResponseWrapper bufferedResponse = new BufferedResponseWrapper((HttpServletResponse) pageContext.getResponse());
		return include(jsp, pageContext, bufferedResponse);
	}
	
	public static String include(String jsp, PageContext pageContext, BufferedResponseWrapper bufferedResponse) throws Exception {
		return include(jsp, pageContext, pageContext.getRequest(), bufferedResponse);
	}
	
	public static String include(String jsp, PageContext pageContext, ServletRequest request, BufferedResponseWrapper bufferedResponse) throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		pageContext.getOut().flush();
		dispatcher.include(request, bufferedResponse);
		pageContext.getOut().flush();
		if (bufferedResponse.getException() != null)
			throw bufferedResponse.getException();
		return bufferedResponse.toString();
	}
	
	public static String requestToJSON(ServletRequest request) throws Exception {
		JSONObject jsonObj = new JSONObject();
		for (Enumeration pEnum = request.getParameterNames(); pEnum.hasMoreElements();) {
			String key = (String) pEnum.nextElement();
			String[] values = request.getParameterValues(key);
			if (values != null) {
				if (values.length == 1) {
					jsonObj.put(key, values[0]);
				} else {
					JSONArray arrJson = new JSONArray();
					for (int i = 0; i < values.length; i++) {
						arrJson.add(values[i]);
					}
					jsonObj.put(key, arrJson);
				}
			} else {
				String value = request.getParameter(key);
				jsonObj.put(key, value);
			}
		}
		return jsonObj.toString();
	}
}
