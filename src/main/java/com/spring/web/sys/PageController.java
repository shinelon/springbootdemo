package com.spring.web.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PageController extends BaseController {
	@RequestMapping(value = "/getpage/{page}")
	public String getPage(@PathVariable String page){
		log.info("getPage:{}",page);
		return page;
	}
}
