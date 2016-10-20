package com.spring.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 
 * @author syq
 * 
 * 获取配置文件的数据
 * 
 * */
@Component
public class FileProperties {

	@Value("${app.name}")
	private String appName;

	@Value("${app.description}")
	private String appdescription;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppdescription() {
		return appdescription;
	}

	public void setAppdescription(String appdescription) {
		this.appdescription = appdescription;
	}
	
	

}
