package com.daumit.daummng.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.dao.ResultSetData;
import com.daumit.daummng.dao.DaumMngDao;

@Service("DaumMngService")
public class DaumMngServiceImpl implements DaumMngService {
	
	@Resource(name="DaumMngDao")
	private DaumMngDao daumMngDao;
	
	public List<Map<String, Object>> selectCopy(List<Object> list) {
		List<Map<String, Object>> result = daumMngDao.selectCopy(list);
		return result;
	}
	
	public List<Map<String, Object>> selectBBS() {
		List<Map<String, Object>> result = daumMngDao.selectBBS();
		return result;
	}
	
	public Map<String, Object> selectAuth(Map<String, Object> map) {
		ResultSetData result = daumMngDao.selectAuth(map);
		System.out.println(result.get("CODE_NAME"));
		return (Map<String, Object>) result.get();
	}
	
	public int selectUser(String string) {
		ResultSetData result = daumMngDao.selectUser(string);
		
		System.out.println(result.getRow());
		
		result.beforeFirst();
		System.out.println(result.getRow());
		
		while (result.next()) {
			String role = result.getString("role");
			System.out.println(role);
		}
		
		result.last();
		System.out.println("row count : " + result.getRow());
		
		result.afterLast();
		System.out.println(result.getRow());
		
		return result.getRow() - 1;
	}
	
	public boolean insertFile(Map<String, String> pMap) {
		boolean result = daumMngDao.insertFile(pMap);
		return result;
	}
}
