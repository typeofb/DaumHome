package com.components.jobsch;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

public class JSFactory {

	private static Scheduler scheduler;
	
	public static Scheduler getScheduler() {
		return scheduler;
	}
	
	public void initialize(File propertyContextDir) {
		try {
			SchedulerFactory schedFact = new StdSchedulerFactory(propertyContextDir.getAbsolutePath());
			System.out.print("Initializing Neo Scheduler with Quartz JobScheduler Info..");
			
			scheduler = schedFact.getScheduler();
			
			System.out.println("ok");
			System.out.println("Scheduler InstanceId = " + scheduler.getSchedulerInstanceId());
			System.out.println("Scheduler InstanceName = " + scheduler.getSchedulerName());
			System.out.println("Scheduler MetaData = \n" + scheduler.getMetaData().toString());
			System.out.println("Scheduler ExecutingJobs Count = " + scheduler.getCurrentlyExecutingJobs().size());
			System.out.println("Scheduler JobGroupNames Count = " + scheduler.getJobGroupNames().size());
//			for (int i = 0; i < scheduler.getJobGroupNames().size(); i++) {
//				System.out.println("\t" + scheduler.getJobGroupNames().get(i));
//			}
			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.groupEquals(groupName))) {
					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();
					
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
					Date nextFireTime = triggers.get(0).getNextFireTime();
					
					System.out.println("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - " + nextFireTime);
				}
			}
		} catch (SchedulerException se) {
			se.printStackTrace();
			System.out.println("Initializing Neo Scheduler with Quartz JobScheduler Info..[ERROR]");
			System.out.println(se.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void addListener() {
		try {
			System.out.print("Initializing global job listener..");
			JSGlobalJobListener jobListener = new JSGlobalJobListener();
			scheduler.getListenerManager().addJobListener(jobListener);
			System.out.println("ok");
			
			System.out.print("Initializing global trigger listener..");
			JSGlobalTriggerListener triggerListener = new JSGlobalTriggerListener();
			scheduler.getListenerManager().addTriggerListener(triggerListener);
			System.out.println("ok");
		} catch (SchedulerException se) {
			se.printStackTrace();
			System.out.print("Initializing global listener..[ERROR]");
			System.out.println(se.getMessage());
		}
	}
}
