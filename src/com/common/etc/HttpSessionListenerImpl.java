package com.common.etc;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpSessionListenerImpl implements HttpSessionListener {

	private Log log = LogFactory.getLog(getClass());
	
	@Override
	public void sessionCreated(HttpSessionEvent e) {
		HttpSession session = e.getSession();
		log.info("sessionCreated : " + session.getId());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		HttpSession session = e.getSession();
		log.info("sessionDestroyed : " + session.getId());
	}
}
