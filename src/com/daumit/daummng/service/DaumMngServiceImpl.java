package com.daumit.daummng.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
		Map<String, Object> result = daumMngDao.selectAuth(map);
		return result;
	}
	
	public int selectUser(String string) {
		int result = daumMngDao.selectUser(string);
		return result;
	}
	
	public boolean insertFile(Map<String, String> pMap) {
		boolean result = daumMngDao.insertFile(pMap);
		return result;
	}
}
