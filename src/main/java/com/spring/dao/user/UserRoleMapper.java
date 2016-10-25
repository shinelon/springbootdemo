package com.spring.dao.user;

import com.spring.domain.user.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}