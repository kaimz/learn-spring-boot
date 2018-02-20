package com.wuwii.module.sys.controller;

import com.wuwii.module.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 控制器基类， 主要负责当前认证用户
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/11 21:08</pre>
 */
public abstract class BaseController {
    /**
     * logger
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getId();
    }
}
