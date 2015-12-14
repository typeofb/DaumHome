package com.components.chat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ChatStartListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		new ChatServer().start();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
