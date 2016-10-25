package com.spring.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.spring.domain.user.User;

@Mapper
public interface UserMapperAnnotation {
	@Select("SELECT * FROM t_user WHERE username = #{username}")	
	public User findByUsername(@Param("username")String username);
}
