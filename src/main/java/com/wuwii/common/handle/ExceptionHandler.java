package com.wuwii.common.handle;

import com.wuwii.common.exception.KCException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

/**
 * 表示 注解 RestController 的异常统一处理
 * <p>
 * 首先处理已知异常，
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/2 23:33</pre>
 */
@RestControllerAdvice
public class ExceptionHandler {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 处理系统自定义的异常
     *
     * @param e 异常
     * @return 状态码和错误信息
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(KCException.class)
    public ResponseEntity<String> handleKCException(KCException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }

    /**
     * 参数检验违反约束（数据校验）
     *
     * @param e BindException
     * @return error message
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleConstraintViolationException(BindException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(BAD_REQUEST).body(
                e.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(",")));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(CONFLICT).body("数据库中已存在该记录");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(LockedAccountException.class)
    public ResponseEntity<String> handleLockedAccountException(LockedAccountException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(UNAUTHORIZED).body("账号已被锁定,请联系管理员");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CredentialsException.class)
    public ResponseEntity<String> handleCredentialsException(CredentialsException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(UNAUTHORIZED).body("登陆信息失效，请重新登录");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<String> handleAuthorizationException(AuthorizationException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(UNAUTHORIZED).body("没有权限，请联系管理员授权");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.debug(e.getMessage(), e);
        return ResponseEntity.status(BAD_REQUEST).body("请求错误！");
    }

    /**
     * 处理异常
     *
     * @param e 异常
     * @return 状态码
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }
}
