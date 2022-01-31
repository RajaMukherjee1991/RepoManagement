package com.repo.gbj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

	@RequestMapping
	public String displaySearchPage(ModelMap map){
		return "users/fingerdata";
	}
}
