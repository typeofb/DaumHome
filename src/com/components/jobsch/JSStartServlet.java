package com.components.jobsch;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.SchedulerException;

public class JSStartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		JSFactory jsFactory = new JSFactory();
		File propertyContextDir = new File(config.getServletContext().getRealPath("/WEB-INF/quartz.properties"));
		jsFactory.initialize(propertyContextDir);
		
		System.out.print("Quartz starting..");
		try {
			// qrtz_triggers 테이블에 저장된 JOB 실행(You can find the SQL scripts in the <quartz home>/docs/dbTables directory)
			JSFactory.getScheduler().start();
			System.out.print(JSFactory.getScheduler().isStarted() + "..\n\n");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		JSFactory.addListener();
	}
}
