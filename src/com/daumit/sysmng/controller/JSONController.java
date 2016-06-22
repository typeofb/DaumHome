package com.daumit.sysmng.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daumit.sysmng.dto.BoardMngDto;

@Controller
public class JSONController {
	
	private Log log = null;
	
	public JSONController() {
		log = LogFactory.getLog(getClass());
	}
	
	@RequestMapping(value="json")
	public void json(ModelMap modelMap) {
		log.info("console - json");
		
		List<BoardMngDto> list = new ArrayList<BoardMngDto>();
		BoardMngDto dto = new BoardMngDto();
		dto.setContents("[{\"fakeNm\":\"/mobile/file/download/0/1466388349809\",\"realNm\":\"Chrysanthemum.jpg\"},{\"fakeNm\":\"/mobile/file/download/0/1466388310261\",\"realNm\":\"Desert.jpg\"},{\"fakeNm\":\"/mobile/file/download/0/1466387904304\",\"realNm\":\"Hydrangeas.jpg\"}]");
		BoardMngDto dto2 = new BoardMngDto();
		dto2.setContents("[{\"fakeNm\":\"/mobile/file/download/14/1466388349809\",\"realNm\":\"Koala.jpg\"},{\"fakeNm\":\"/mobile/file/download/14/1466388310261\",\"realNm\":\"Lighthouse.jpg\"}]");
		list.add(dto);
		list.add(dto2);
		
		modelMap.put("result", list);
	}
}
