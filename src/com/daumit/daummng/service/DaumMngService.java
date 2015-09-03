package com.daumit.daummng.service;

import java.util.List;
import java.util.Map;

import com.common.dao.ResultSetData;

public interface DaumMngService {

	public ResultSetData selectCopy(List<Object> list);
	public ResultSetData selectBBS();
	public Map<String, Object> selectAuth(Map<String, Object> map);
	public int selectUser(String string);
}
