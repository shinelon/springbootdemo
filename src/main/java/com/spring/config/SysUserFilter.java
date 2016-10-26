package com.spring.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.domain.user.User;
import com.spring.service.sys.UserService;
import com.spring.utils.Constants;

/**
 * 这里获取并保存登录用户信息
 */
public class SysUserFilter extends PathMatchingFilter {
    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest servletRequest, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUsername(username);
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        request.getSession().setAttribute(Constants.CURRENT_USER, user);
        return super.onPreHandle(request, response, mappedValue);
    }
}
