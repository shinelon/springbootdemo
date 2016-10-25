package com.spring.dao.user;

import com.spring.domain.user.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}