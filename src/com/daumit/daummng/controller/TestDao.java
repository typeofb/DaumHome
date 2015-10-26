package com.daumit.daummng.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.StaticWebApplicationContext;

import com.daumit.daummng.dao.OracleDaoImpl;

public class TestDao {

	public static void main(String[] args) {
		MockServletContext servletContext = new MockServletContext("war", new FileSystemResourceLoader()); // WebContent->war
		StaticWebApplicationContext parent = new StaticWebApplicationContext();
//		ContextLoader contextLoader = new ContextLoader();
		HoneContextLoader contextLoader = new HoneContextLoader();
		servletContext.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "WEB-INF/applicationContext.xml, WEB-INF/dispatcher-servlet.xml");
		parent.refresh();
//		ApplicationContext applicationContext = (ApplicationContext) contextLoader.initWebApplicationContext(servletContext);
		ApplicationContext applicationContext = (ApplicationContext) contextLoader.createWebApplicationContext(servletContext, parent);
		
		// 테스트 실행
		OracleDaoImpl oracleDaoImpl = (OracleDaoImpl) applicationContext.getBean("oracleDao");
		oracleDaoImpl.select("15072701");
	}
}

class HoneContextLoader extends ContextLoader {
	public WebApplicationContext createWebApplicationContext(ServletContext servletContext, ApplicationContext parent) {
		Class contextClass = determineContextClass(servletContext);
		if (!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
			throw new ApplicationContextException("Custom context class [" + contextClass.getName() + "] is not of type [" + ConfigurableWebApplicationContext.class.getName() + "]");
		}
		
		ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);
		wac.setParent(parent);
		wac.setServletContext(servletContext);
		String configLocation = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
		if (configLocation != null) {
			wac.setConfigLocations(StringUtils.tokenizeToStringArray(configLocation, ConfigurableWebApplicationContext.CONFIG_LOCATION_DELIMITERS));
		}
		customizeContext(servletContext, wac);
		wac.refresh();
		
		return wac;
	}
}
