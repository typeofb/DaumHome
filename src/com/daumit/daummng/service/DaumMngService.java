package com.daumit.daummng.service;

import java.util.List;
import java.util.Map;

import com.daumit.daummng.dao.DaumMngDao;

public class DaumMngService {
	
	private DaumMngDao dao = null;
	
	public DaumMngService() {
		dao = new DaumMngDao();
	}
	
	public List<Integer> selectCopy(List<Integer> list) {
		List<Integer> result = dao.selectCopy(list);
		return result;
	}
	
	public List<Map<String, Object>> selectBBS(Map<String, Object> map) {
		List<Map<String, Object>> result = dao.selectBBS(map);
		return result;
	}
	
	public Map<String, Object> selectAuth(Map<String, Object> map) {
		Map<String, Object> result = dao.selectAuth(map);
		return result;
	}
	
	public int selectUser(String string) {
		int result = dao.selectUser(string);
		return result;
	}
	
	public boolean insertFile(Map<String, String> pMap) {
		boolean result = dao.insertFile(pMap);
		return result;
	}
}
