package com.wuwii.common.config;

import com.wuwii.module.sys.common.autho2.OAuth2Filter;
import com.wuwii.module.sys.common.autho2.OAuth2Realm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，
 * 所以我们需要定义一系列关于URL的规则和访问权限。
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 10:35</pre>
 */
@Configuration
public class ShiroConfig {
    /**
     * 密码加密迭代次数
     */
    public final static int hashIterations = 1;

    /**
     * 会话工厂
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    /**
     * 注册安全管理,必须设置 SecurityManager
     *
     * @param oAuth2Realm    认证
     * @param sessionManager 缓存
     * @return
     */
    @Bean
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 可以添加多个认证，执行顺序是有影响的
        securityManager.setRealm(oAuth2Realm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager, OAuth2Filter oAuth2Filter) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //自定义一个oauth2拦截器，不设置就是使用默认的拦截器
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", oAuth2Filter);
        shiroFilter.setFilters(filters);
        //拦截器
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        Map<String, String> filterMap = new LinkedHashMap<>();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterMap.put("/sys/logout", "logout");
        // 验证码
        filterMap.put("/captcha.jpg", "anon");
        // 设置系统模块下访问需要权限
        filterMap.put("/login", "anon");
        // 自定义的拦截
        filterMap.put("/sys/**", "oauth2");

        // filterMap.put("/sys/**", "authc");
        // 登陆的 url
        shiroFilter.setLoginUrl("/sys/login");
        // 登陆成功跳转的 url
        shiroFilter.setSuccessUrl("/");
        // 未授权的 url
        // shiroFilter.setUnauthorizedUrl("/login.html");
        //未授权界面;
        shiroFilter.setUnauthorizedUrl("/403");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    /**
     * Shiro生命周期处理器
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解，
     * (如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,
     * 并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 开启 shiro aop注解支持.
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * <b>需要在身份认证中添加 realm.setCredentialsMatcher(hashedCredentialsMatcher())</b>
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");//散列算法:MD2、MD5、SHA1、SHA256、SHA384、SHA512等。
        hashedCredentialsMatcher.setHashIterations(hashIterations);//散列的次数，默认1次， 设置两次相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 注册身份验证
     *
     * @param hashedCredentialsMatcher 凭证匹配器
     * @return
     */
    @Bean
    public OAuth2Realm oAuth2Realm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        OAuth2Realm oAuth2Realm = new OAuth2Realm();
        oAuth2Realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return oAuth2Realm;
    }

    @Bean
    public OAuth2Filter oAuth2Filter() {
        return new OAuth2Filter();
    }



}