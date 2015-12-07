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
	
	public List<Map<String, Object>> selectCodeList(Map<String, Object> paramMap) {
		List<Map<String, Object>> result = dao.selectCodeList(paramMap);
		return result;
	}
	
	public boolean insertCode(Map<String, Object> paramMap) {
		boolean result = dao.insertCode(paramMap);
		return result;
	}
	
	public boolean updateCode(Map<String, Object> paramMap) {
		boolean result = dao.updateCode(paramMap);
		return result;
	}
	
	public boolean deleteCode(Map<String, Object> paramMap) {
		boolean result = dao.deleteCode(paramMap);
		return result;
	}
}
