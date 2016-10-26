package com.spring.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class SysFormAuthenticationFilter extends FormAuthenticationFilter {
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if(request.getAttribute(getFailureKeyAttribute()) != null) { //如果验证已经失败，就不走下面的用户认证
            return true; //这里需要返回true跳转到loginController进行处理
        }
		return super.onAccessDenied(request, response);
	}
	/**
	 * localhost:8080/aaa
	 * 登录成功后跳转到successUrl,否则跳转到aaa
	 * */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		WebUtils.issueRedirect(request, response, getSuccessUrl());
		return false;
	}
}
