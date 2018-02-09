package com.wuwii.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

/**
 * 表示 注解 RestController 的异常统一处理
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
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(CONFLICT).body("数据库中已存在该记录");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<String> handleAuthorizationException(AuthorizationException e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(UNAUTHORIZED).body("没有权限，请联系管理员授权");
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
