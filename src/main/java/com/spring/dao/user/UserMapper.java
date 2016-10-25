package com.spring.dao.user;

import org.apache.ibatis.annotations.Mapper;

import com.spring.domain.user.User;
@Mapper
public interface UserMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findByUsername(String username);
}