package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysUserEntity;
import com.wuwii.module.sys.entity.SysUserTokenEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.Set;

/**
 * shiro 权限验证所需要的业务
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 15:31</pre>
 */
@CacheConfig(cacheNames = "users")
public interface ShiroService {
    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return 权限
     */
    @Cacheable
    Set<String> getUserPermissions(long userId);

    /**
     * 根据 token 查询 token 实体类
     *
     * @param token token
     * @return SysUserTokenEntity
     */
    SysUserTokenEntity getTokenObjectByToken(String token);

    SysUserEntity getUserById(long userId);
}
