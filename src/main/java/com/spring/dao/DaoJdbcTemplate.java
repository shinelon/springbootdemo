package com.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Student;

@Repository
public class DaoJdbcTemplate {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private MybatisMapperTest mybatisMapperTest;

	public Integer test() {
		
		return jdbcTemplate.queryForObject("select 1 from dual", Integer.class);
	}
	public String testTransactional(){
		jdbcTemplate.update("UPDATE course SET NAME ='trddd' WHERE id=1");
		jdbcTemplate.update("UPDATE course SET id=2 WHERE id=1");
		return "ok";
	}
	public String testMybatis(String name){
		Student student=mybatisMapperTest.findByName(name);
		return "ok";
	}
	
}
