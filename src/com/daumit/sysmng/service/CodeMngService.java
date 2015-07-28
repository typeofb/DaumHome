package com.daumit.sysmng.service;

import java.util.HashMap;
import java.util.List;

import com.daumit.sysmng.dao.CodeMngDao;

public class CodeMngService {
	
	private CodeMngDao dao = null;
	
	public CodeMngService() {
		dao = new CodeMngDao();
	}
	
	public List<HashMap<String, Object>> selectAreaList(int targetPage) {
		List<HashMap<String, Object>> result = dao.selectAreaList(targetPage);
		return result;
	}
	
	public boolean insertArea(HashMap<String, Object> iMaps) {
		boolean result = dao.insertArea(iMaps);
		return result;
	}
	
	public boolean updateArea(HashMap<String, Object> iMaps) {
		boolean result = dao.updateArea(iMaps);
		return result;
	}
	
	public boolean deleteArea(String postId) {
		boolean result = dao.deleteArea(postId);
		return result;
	}
}
