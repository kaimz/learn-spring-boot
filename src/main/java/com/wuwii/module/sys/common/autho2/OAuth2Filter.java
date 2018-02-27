package com.wuwii.module.sys.common.autho2;

import com.wuwii.common.exception.KCException;
import com.wuwii.module.sys.common.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * oauth2拦截器，现在改为 JWT 认证
 */
public class OAuth2Filter extends FormAuthenticationFilter {
    /**
     * 设置 request 的键，用来保存 认证的 userID,
     */
    private final static String USER_ID = "USER_ID";
    @Resource
    private JwtUtils jwtUtils;

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Filter.class);


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
        String token = getRequestToken((HttpServletRequest) request);
        try {
            // 检查 token 有效性
            //ExpiredJwtException JWT已过期
            //SignatureException JWT可能被篡改
            Jwts.parser().setSigningKey(jwtUtils.getSecret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            // 身份验证失败，返回 false 将进入onAccessDenied 判断是否登陆。
            onLoginFail(response);
            return false;
        }
        Long userId = getUserIdFromToken(token);
        // 存入到 request 中，在后面的业务处理中可以使用
        request.setAttribute(USER_ID, userId);
        return true;
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
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            onLoginFail(response);
            return false;
        }
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
            ((HttpServletResponse) response).setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().print("账号活密码错误");
        } catch (IOException e1) {
            LOGGER.error(e1.getMessage(), e1);
        }
        return false;
    }

    /**
     * token 认证失败
     *
     * @param response
     */
    private void onLoginFail(ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
        try {
            response.getWriter().print("没有权限，请联系管理员授权");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader(jwtUtils.getHeader());
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            return httpRequest.getParameter(jwtUtils.getHeader());
        }
        if (StringUtils.isBlank(token)) {
            // 从 cookie 获取 token
            Cookie[] cookies = httpRequest.getCookies();
            if (null == cookies || cookies.length == 0) {
                return null;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(jwtUtils.getHeader())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    /**
     * 根据 token 获取 userID
     *
     * @param token token
     * @return userId
     */
    private Long getUserIdFromToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new KCException("无效 token", HttpStatus.UNAUTHORIZED.value());
        }
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            throw new KCException(jwtUtils.getHeader() + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }
        return Long.parseLong(claims.getSubject());
    }

}
