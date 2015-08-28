package com.daumit.daummng.service;

import java.util.List;
import java.util.Map;

public interface DaumMngService {

	public List<Map<String, Object>> selectCopy(List<Object> list);
	public List<Map<String, Object>> selectBBS();
	public Map<String, Object> selectAuth(Map<String, Object> map);
	public int selectUser(String string);
}
