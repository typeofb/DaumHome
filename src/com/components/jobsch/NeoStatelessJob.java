package com.components.jobsch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public abstract class NeoStatelessJob implements Job {

	private String curJobName;
	private String curInstance;
	
	public String getCurJobName() {
		return curJobName;
	}
	
	public String getCurInstance() {
		return curInstance;
	}
	
	public void initValues(String curJobName, String curInstance) {
		this.curJobName = curJobName;
		this.curInstance = curInstance;
	}
	
	public abstract void runJob(JobExecutionContext jobContext);
	
	@Override
	public void execute(JobExecutionContext context) {
		try {
			this.initValues(context.getJobDetail().getKey().getName(), System.getProperty("serverName"));
			runJob(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
