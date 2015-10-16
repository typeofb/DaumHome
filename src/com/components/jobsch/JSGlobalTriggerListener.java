package com.components.jobsch;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

public class JSGlobalTriggerListener implements TriggerListener {

	private final String listenerName = "NeoGlobalTriggerListener";
	
	@Override
	public String getName() {
		return listenerName;
	}
	
	@Override
	public void triggerComplete(Trigger arg0, JobExecutionContext arg1, CompletedExecutionInstruction arg2) {
		try {
			System.out.println("\n[" + arg0.getJobKey().getName() + "] triggerComplete!!");
//			System.out.println("[" + arg0.getJobKey().getName() + "] Instance Id = " + arg1.getScheduler().getSchedulerInstanceId());
			System.out.println("[" + arg0.getJobKey().getName() + "] execution result - " + arg1.getResult());
//			System.out.println("[" + arg0.getJobKey().getName() + "] getMisfireInstruction - " + arg0.getMisfireInstruction());
//			System.out.println("[" + arg0.getJobKey().getName() + "] Trigger state = " + arg1.getScheduler().getTriggerState(arg0.getKey()));
			System.out.println("#####################################################################################");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void triggerFired(Trigger arg0, JobExecutionContext arg1) {
		try {
			System.out.println("\n#####################################################################################");
			System.out.println("[" + arg0.getJobKey().getName() + "] triggerFired!! name = " + arg0.getJobKey().getName());
			System.out.println("[" + arg0.getJobKey().getName() + "] Instance Id = " + arg1.getScheduler().getSchedulerInstanceId());
			System.out.println("[" + arg0.getJobKey().getName() + "] getMisfireInstruction - " + arg0.getMisfireInstruction());
			System.out.println("[" + arg0.getJobKey().getName() + "] Trigger state = " + arg1.getScheduler().getTriggerState(arg0.getKey()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void triggerMisfired(Trigger arg0) {
		System.out.println("\n[" + arg0.getJobKey().getName() + "] Misfired!! #############################");
	}
	
	@Override
	public boolean vetoJobExecution(Trigger arg0, JobExecutionContext arg1) {
		try {
			System.out.println("\n[" + arg0.getJobKey().getName() + "] vetoJobExecution!!");
//			System.out.println("[" + arg0.getJobKey().getName() + "] Instance Id = " + arg1.getScheduler().getSchedulerInstanceId());
//			System.out.println("[" + arg0.getJobKey().getName() + "] execution result - " + arg1.getResult());
//			System.out.println("[" + arg0.getJobKey().getName() + "] getMisfireInstruction - " + arg0.getMisfireInstruction());
//			System.out.println("[" + arg0.getJobKey().getName() + "] Trigger state = " + arg1.getScheduler().getTriggerState(arg0.getKey()));
			
			if (arg1.getScheduler().isInStandbyMode() || arg1.getScheduler().isShutdown()) {
				System.out.println("Scheduler is shutdown!! restart!!");
				arg1.getScheduler().start();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
