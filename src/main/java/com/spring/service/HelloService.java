package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.DaoJdbcTemplate;
import com.spring.dao.MybatisMapperTest;
import com.spring.domain.Student;

@Service
public class HelloService {
	@Autowired
	private DaoJdbcTemplate daoJdbcTemplate;
	@Autowired
	private MybatisMapperTest mybatisMapperTest;
	
	public String getMethod(){
		return "service getMethod";
	}
	@Transactional
	public Integer getDaoTest(){
		return daoJdbcTemplate.test();
	}
	/**
	 * 日志配置文件<Logger name="org.springframework" level="debug" />可以看见事务日志
	 ***/
	@Transactional
	public String getDaoTestTransactional(){
		return daoJdbcTemplate.testTransactional();
	}
	
	@Transactional
	public Student getMybatisStudent(String name){
		return mybatisMapperTest.findByName(name);
	}
}
