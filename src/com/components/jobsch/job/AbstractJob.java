package com.components.jobsch.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import com.components.jobsch.NeoStatefulJob;

public abstract class AbstractJob extends NeoStatefulJob {

	public abstract String getHandlerBeanName();
	
	public abstract String getCompanyCode();
	
	@Override
	public void runJob(JobExecutionContext jobContext) {
		System.out.println("AbstractJob..[START]");
		String handlerBeanName = getHandlerBeanName();
		String companyCode = getCompanyCode();
		if (jobContext != null) {
			JobDataMap jobDataMap = jobContext.getMergedJobDataMap();
			if (jobDataMap != null && jobDataMap.containsKey("handlerBeanName")) {
				handlerBeanName = jobDataMap.getString("handlerBeanName");
			}
			
			JobDataMap jobDataMap2 = jobContext.getMergedJobDataMap();
			if (jobDataMap2 != null && jobDataMap2.containsKey("companyCode")) {
				companyCode = jobDataMap2.getString("companyCode");
			}
		}
		System.out.println(handlerBeanName);
		System.out.println(companyCode);
		System.out.println("AbstractJob..[END]");
	}
}
