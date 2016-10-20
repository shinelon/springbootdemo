package com.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.spring.domain.Student;

@Mapper
public interface MybatisMapperTest {
	
	@Select("SELECT * FROM STUDENT WHERE NAME = #{name}")
    Student findByName(@Param("name") String name);
}
