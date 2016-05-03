package com.daumit.sysmng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.web.db.SqlConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SqlConfig.class, loader = AnnotationConfigContextLoader.class)
public class CodeMngServiceTest {

//	@Autowired
	private CodeMngService codeMngService = new CodeMngService();
	
	@Test
	public void testSelectCodeList() {
		List<Map<String, Object>> result = codeMngService.selectCodeList(new HashMap<String, Object>());
		System.out.println(result);
	}
}
