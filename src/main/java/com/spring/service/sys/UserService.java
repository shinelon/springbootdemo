package com.spring.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.user.UserMapper;
import com.spring.domain.user.User;
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	public User selectByPrimaryKey(Long i) {
		return userMapper.selectByPrimaryKey(i);
	}

}
