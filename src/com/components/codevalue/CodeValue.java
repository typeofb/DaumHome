package com.components.codevalue;

import java.util.HashMap;

import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.context.ApplicationContext;

public class CodeValue extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;
	
	public String getString(String key) {
		return super.get(key);
	}
	
	public String getString(String key, String defaultValue) {
		String value = super.get(key);
		if (value == null || value.trim().length() == 0) {
			value = defaultValue;
		}
		return value;
	}
	/*
	public boolean getBoolean(String key) {
		return EPUtils.isTrue(super.get(key));
	}
	
	public boolean containsKey(String key) {
		return super.containsKey(key);
	}
	
	public boolean hasRole() {
		EPUser epUser = SessionUtils.getUser();
		if (epUser == null)
			return false;
		return ((containsKey("all")
				|| containsKey("c_" + epUser.getCompanyId())
				|| containsKey("d_" + epUser.getCurrentDept().getDeptId())
				|| containsKey("u_" + epUser.getUserId())));
	}
	
	public boolean hasRole(String checkId) {
		return ((containsKey("all")
				|| containsKey("c_" + checkId)
				|| containsKey("d_" + checkId)
				|| containsKey("u_" + checkId)));
	}
	
	public boolean hasRoleByKeyword(String columnName, String keyword) {
		ApplicationContext apContext = ApplicationContextUtils.getApplicationContext(true);
		OrgDao orgDao = (OrgDao) apContext.getBean("orgDao");
		VWUser vwUser = orgDao.getUser(columnName, keyword);
		
		if (vwUser != null) {
			return ((containsKey("all")
					|| containsKey("c_" + vwUser.getCompanyId())
					|| containsKey("d_" + vwUser.getDeptId())
					|| containsKey("u_" + vwUser.getUserId())));
		} else
			return false;
	}*/
}
