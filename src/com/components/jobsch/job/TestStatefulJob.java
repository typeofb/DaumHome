package com.components.jobsch.job;

import org.quartz.JobExecutionContext;
import com.components.jobsch.NeoStatefulJob;

public class TestStatefulJob extends NeoStatefulJob {

	@Override
	public void runJob(JobExecutionContext jobContext) {
		System.out.println(TestStatefulJob.class.toString());
		System.out.println(TestStatefulJob.class.getCanonicalName());
	}

	public static void main(String[] args) {
		TestStatefulJob job = new TestStatefulJob();
		job.runJob(null);
	}
}
