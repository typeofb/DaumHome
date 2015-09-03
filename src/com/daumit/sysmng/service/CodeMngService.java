package com.daumit.sysmng.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.daumit.sysmng.dao.CodeMngDao;

@Service("CodeMngService")
public class CodeMngService {
	
	@Resource(name="CodeMngDao")
	private CodeMngDao dao;
	
	public CodeMngService() {
		dao = new CodeMngDao();
	}
	
	public List<Map<String, Object>> selectAreaList(int targetPage) {
		List<Map<String, Object>> result = dao.selectAreaList(targetPage);
		return result;
	}
	
	public boolean insertArea(Map<String, Object> paramMap) {
		boolean result = dao.insertArea(paramMap);
		return result;
	}
	
	public boolean updateArea(Map<String, Object> paramMap) {
		boolean result = dao.updateArea(paramMap);
		return result;
	}
	
	public boolean deleteArea(String postId) {
		boolean result = dao.deleteArea(postId);
		return result;
	}
}
