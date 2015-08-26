package com.daumit.daummng.service;

import java.util.List;
import java.util.Map;

public interface DaumMngService {

	public List<Map<String, Object>> selectCopy(List<Object> list);
	public List<Map<String, Object>> selectBBS();
}
