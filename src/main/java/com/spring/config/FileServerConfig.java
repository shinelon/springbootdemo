package com.spring.config;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.web.sys.BaseController;

@Configuration
public class FileServerConfig {
	@Value("${upload.maxSize}")
    private String maxSize;
	private static final Logger log = LoggerFactory.getLogger(FileServerConfig.class);
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(maxSize);
        factory.setMaxRequestSize(maxSize);
        log.info("file maxSize:{}",maxSize);
        return factory.createMultipartConfig();
    }
}
