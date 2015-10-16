package com.components.jobsch.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

public class TestJob extends AbstractJob {

	@Override
	public String getHandlerBeanName() {
		return "test";
	}
	
	@Override
	public String getCompanyCode() {
		return "301";
	}
	
	public static void main(String[] args) throws SchedulerException {
//		TestJob job = new TestJob();
//		job.runJob(null);
		
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		Scheduler sched = schedFact.getScheduler();
		sched.start();
		
		// define the job and tie it to our HelloJob class
		JobDetail job = newJob(TestJob.class).withIdentity("myJob", "group1").build();
		
		// Trigger the job to run now, and then every 10 seconds
		Trigger trigger = newTrigger().withIdentity("myTrigger", "group1").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();
		
		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, trigger);
	}
}
