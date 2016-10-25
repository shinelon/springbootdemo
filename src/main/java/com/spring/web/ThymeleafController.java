package com.spring.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.config.Layout;

@Controller
@Layout(Layout.NONE)
public class ThymeleafController {

	@RequestMapping("/thymeleaf")
	
	String index(Model model) {
		model.addAttribute("now", LocalDateTime.now());
		model.addAttribute("msg", "hello");
		return "pages/index";
	}
	@RequestMapping("/thymeleaf/home")
	String home(Model model) {
		model.addAttribute("now", LocalDateTime.now());
		model.addAttribute("msg", "hello");
		return "home/home";
	}

	@RequestMapping("/thymeleaf/properties")
	@ResponseBody
	java.util.Properties properties() {
		return System.getProperties();
	}
	@RequestMapping("/thymeleaf/error")
	String error(Model model) throws Exception {
		return "123";
	}
}
