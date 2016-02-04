package com.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class AuthenticationEntryPointImpl extends LoginUrlAuthenticationEntryPoint {

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		super.commence(request, response, authException);
	}
	
	public String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
		return "/login_main.do";
	}
}
