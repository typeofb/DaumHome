package com.components.jobsch;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public abstract class NeoStatefulJob implements Job {

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
			this.initValues(context.getJobDetail().getKey().getName(), context.getScheduler().getSchedulerInstanceId());
			runJob(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
