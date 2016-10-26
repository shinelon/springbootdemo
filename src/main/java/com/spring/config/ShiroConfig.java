package com.spring.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;
import com.spring.realm.UserRealm;

@Configuration
public class ShiroConfig {
	
	/**
     * 这里通过代码的方式配置访问路径拦截
     * @param manager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //这里注册默认的拦截器链如authc, anon, ssl 等
        for(DefaultFilter filter : DefaultFilter.values()) {
        	bean.getFilters().put(filter.name(), (Filter) ClassUtils.newInstance(filter.getFilterClass()));
        }
        bean.getFilters().put("sysAuthc", sysFormAuthenticationFilter()); //进行用户身份认证
        bean.getFilters().put("sysUser", sysUserFilter()); //获取登录用户信息
        Map<String, String> chains = Maps.newLinkedHashMap();
//      chains.put("/user/list", "authc, roles[admin]");
        // logout已经实现了
        chains.put("/logout", "logout");
        //获取静态资源部验证
        chains.put("/static/**", "anon");
//        chains.put("/login", "sysAuthc");
//        chains.put("/", "user, sysUser");
//        chains.put("/**", "user, sysUser");
//        chains.put("/login", "user");
        chains.put("/", "user");
        chains.put("/**", "user");
        
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/403");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }
    @Bean
    public SysUserFilter sysUserFilter() {
        return new SysUserFilter();
    }
    @Bean
    public SysFormAuthenticationFilter sysFormAuthenticationFilter() {
        return new SysFormAuthenticationFilter();
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        return matcher;
    }
    /**
     * 开启shiro aop注解，进行权限验证
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor =
                new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
	
	/**
	 * 使用eh管理shiro
	 * */
	@Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
        return manager;
    }
	/**
	 * 定义useRealm
	 * 
	 * */
	@Bean
    public UserRealm UserRealm() {
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
//        realm.setCredentialsMatcher(retryLimitedHashedCredentialsMatcher());
        return realm;
    }
	/**
	 *设置 SecurityManager
	 * */
	@Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(UserRealm()); //注入自定义realm实现
        manager.setCacheManager(ehCacheManager()); //注入ehcacheManager实现缓存机制
        //manager.setRememberMeManager(cookieRememberMeManager()); //设置记住密码
        return manager;
    }
	
}
