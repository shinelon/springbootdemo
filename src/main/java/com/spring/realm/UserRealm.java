package com.spring.realm;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;
import com.spring.domain.user.Permission;
import com.spring.domain.user.Role;
import com.spring.domain.user.User;
import com.spring.service.sys.UserService;

public class UserRealm extends AuthorizingRealm{
	Logger logger = LoggerFactory.getLogger(UserRealm.class);
	
	@Autowired
    private UserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		logger.info("doGetAuthorizationInfo...");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String username = (String) pc.getPrimaryPrincipal();
        User user = userService.findByUsername(username);
        if(user == null) {
            return null;
        }
        List<Role> roles = user.getRoles();
        Set<String> roleNames = Sets.newHashSet();
        Set<String> permissions = Sets.newHashSet();
        for(Role role : roles) {
            roleNames.add(role.getRole());
            for(Permission permission : role.getPermissions()) {
                permissions.add(permission.getPermission());
            }
        }
        logger.info("角色列表=============:{}" , roleNames);
        logger.info("权限列表=============:{}" , permissions);
        info.setRoles(roleNames);
        info.setStringPermissions(permissions);
        return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("身份认证==========>doGetAuthenticationInfo");
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("登录用户不存在");
        }

        if (user.getState() == 2) {
            throw new LockedAccountException("当前用于已经被锁定!");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialSalt()), getName());

        return info;
	}

}
