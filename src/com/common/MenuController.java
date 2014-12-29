package com.common;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public class MenuController {
	
	@ModelAttribute
	public void index(Model model) {
		
		model.addAttribute("menu", "동적메뉴");
	}
}
