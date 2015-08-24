package com.common.dao.support;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public abstract class AbstractDaoOperationSupport implements BeanFactoryAware {

	protected String dataSourceBeanName;
	
	public AbstractDaoOperationSupport() {
		this.dataSourceBeanName = "dataSource";
	}
	
	protected abstract void initTemplate(DataSource dataSource);
	protected abstract boolean isInitialized();
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (isInitialized()) {
			DataSource dataSource = (DataSource)beanFactory.getBean(dataSourceBeanName);
			initTemplate(dataSource);
		}
	}
}
