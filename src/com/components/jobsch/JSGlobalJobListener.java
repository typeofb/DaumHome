package com.components.jobsch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class JSGlobalJobListener implements JobListener {

	private final String listenerName = "NeoGlobalJobListener";
	
	@Override
	public String getName() {
		return listenerName;
	}
	
	@Override
	public void jobExecutionVetoed(JobExecutionContext arg0) {
		System.out.println("jobExecutionVetoed!! - " + arg0.getJobDetail().getKey().getName());
	}
	
	@Override
	public void jobToBeExecuted(JobExecutionContext arg0) {
		try {
			System.out.println("\n[" + arg0.getJobDetail().getKey().getName() + "] To be executed..");
			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Instance Id = " + arg0.getScheduler().getSchedulerInstanceId());
//			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Running time = " + arg0.getJobRunTime());
//			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Execution result = " + arg0.getResult());
			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Fire time = " + arg0.getFireTime());
//			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Next fire time = " + arg0.getNextFireTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void jobWasExecuted(JobExecutionContext arg0, JobExecutionException arg1) {
		try {
			System.out.println("\n[" + arg0.getJobDetail().getKey().getName() + "] was executed..");
//			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Instance Id = " + arg0.getScheduler().getSchedulerInstanceId());
			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Running time = " + arg0.getJobRunTime());
			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Execution result = " + arg0.getResult());
//			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Fire time = " + arg0.getFireTime());
			System.out.println("[" + arg0.getJobDetail().getKey().getName() + "] Next fire time = " + arg0.getNextFireTime());
			
			if (arg1 != null) {
				System.out.println("\n[" + arg0.getJobDetail().getKey().getName() + "] JobExecutionException!! \nReport generate error.. : " + arg1.getMessage());
				arg1.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
