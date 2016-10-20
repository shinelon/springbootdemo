package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@ServletComponentScan
@SpringBootApplication
public class SpringbootdemoApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringbootdemoApplication.class, args);
		//关闭命令行参数设置端口SpringApplication.setAddCommandLineProperties(false) 待整理
	}
}
