package com.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.aspect.WebAspect;
import com.spring.domain.FileProperties;
import com.spring.service.HelloService;
import com.spring.web.sys.BaseController;

@RestController
public class HelloController extends BaseController {
	@Autowired
	private HelloService helloService;
	@Autowired
	private FileProperties fileProperties;
	
	@RequestMapping("/")
    public String home() {
		String ss=helloService.getMethod();
		System.out.println(ss);
		String appName=fileProperties.getAppName();
		String appDescription=fileProperties.getAppdescription();
		System.out.println(appName+"|"+appDescription);
        return "Hello World!";
    }
	
	/**
	 * http://localhost:8080/hello/123?name=name1
	 **/
	@RequestMapping("/hello/{id}")
	public String hello(@PathVariable String id,@RequestParam String name){
		log.info("id:{}",id);
		log.info("name:{}",name);
		return "hello aop";
	}
	@RequestMapping("/hello/jdbc")
	public Object testJdbc(){
		Integer i=helloService.getDaoTest();
		log.info("jdbc:{}",i.toString());
		return "ok";
	}
	@RequestMapping("/hello/jdbc/transactional")
	public Object testJdbcTransactional(){
		String i=helloService.getDaoTestTransactional();
		log.info("jdbc:{}",i.toString());
		return "ok";
	}
	@RequestMapping("/hello/mybatis")
	public Object testMybatis(){
		return responseJson(helloService.getMybatisStudent("张三"));
	}
	@RequestMapping("/hello/jsp")
	public String jsp(){
		return "turntemplate";
	}
}
