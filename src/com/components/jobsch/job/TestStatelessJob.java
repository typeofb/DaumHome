package com.components.jobsch.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class TestStatelessJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) {
		System.out.println(TestStatelessJob.class.toString());
		System.out.println(TestStatelessJob.class.getCanonicalName());
	}

	public static void main(String[] args) {
		TestStatelessJob job = new TestStatelessJob();
		job.execute(null);
	}
}
