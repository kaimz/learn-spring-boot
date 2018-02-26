package com.wuwii.module.sys.common.autho2;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * oauth2过滤器
 */
public class OAuth2Filter extends AuthenticatingFilter {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Filter.class);

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return new OAuth2Token(token);
    }

    /**
     * shiro权限拦截核心方法 返回true允许访问resource，
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    /**
     * 当访问拒绝时是否已经处理了；
     * 如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理完成了，将直接返回即可。
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            ((HttpServletResponse) response).setStatus(401);
            response.getWriter().print("没有权限，请联系管理员授权");
            return false;
        }
        return executeLogin(request, response);
    }

    /**
     * 鉴定失败，返回错误信息
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        try {
            ((HttpServletResponse) response).setStatus(400);
            response.getWriter().print("账号活密码错误1");
        } catch (IOException e1) {
            LOGGER.error(e1.getMessage(), e1);
        }
        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            return httpRequest.getParameter("token");
        }
        // 从 cookie 获取 token
        Cookie[] cookies = httpRequest.getCookies();
        if (null == cookies || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                continue;
            }
        }
        return token;
    }
}
