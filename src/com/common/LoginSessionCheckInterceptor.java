package com.common;

import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginSessionCheckInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		logger.debug("##### interceptor preHandle #####");
		
		StringTokenizer st = new StringTokenizer(request.getRequestURL().toString(), "/");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.equals("main.do") || token.equals("login.do") || token.equals("loginCheck.do") || token.equals("logout.do"))
				return true;
		}

		HttpSession session = request.getSession();
		if (session.getAttribute("loginResult") == null || !session.getAttribute("loginResult").equals("C")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 해 주세요.');");
			out.println("location.href='" + request.getContextPath() + "';");
			out.println("</script>");
			out.flush();
			return false;
		}
		return true;
	}
}