package com.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.config.Layout;

@Controller
@Layout(value = "layouts/default")
public class ThymeleafLayoutController {
	@RequestMapping("index")
    public String defaultLayout() {
        return "index";
    }

    @RequestMapping("simple")
    @Layout("layouts/simple")
    public String simpleLayout() {
        return "simple";
    }

    @RequestMapping("no-layout")
    @Layout(Layout.NONE)
    public String noLayout() {
        return "noLayout";
    }
}
