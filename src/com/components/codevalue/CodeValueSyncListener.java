package com.components.codevalue;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CodeValueSyncListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		new CodeValueSynchronizer().start();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
