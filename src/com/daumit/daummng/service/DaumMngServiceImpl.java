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
	
	public ResultSetData selectCopy(List<Object> list) {
		ResultSetData result = daumMngDao.selectCopy(list);
		
		System.out.println(result.isFirst());
		
		result.beforeFirst();
		System.out.println(result.getRow());
		
		while (result.next()) {
			String wbtitle = result.getString("WBTITLE");
			System.out.println(wbtitle);
		}
		
		result.last();
		System.out.println("row count : " + result.getRow());
		
		System.out.println(result.isLast());
		
		result.afterLast();
		System.out.println(result.getRow());

		return result;
	}
	
	public ResultSetData selectBBS() {
		ResultSetData result = daumMngDao.selectBBS();
		return result;
	}
	
	public Map<String, Object> selectAuth(Map<String, Object> map) {
		Map<String, Object> result = daumMngDao.selectAuth(map);
		return (Map<String, Object>) result;
	}
	
	public int selectUser(String string) {
		return daumMngDao.selectUser(string);
	}
	
	public boolean insertFile(Map<String, String> pMap) {
		boolean result = daumMngDao.insertFile(pMap);
		return result;
	}
}
