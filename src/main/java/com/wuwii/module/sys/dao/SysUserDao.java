package com.wuwii.module.sys.dao;

import com.wuwii.module.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
@Mapper
@CacheConfig(cacheNames = "users")
public interface SysUserDao extends BaseDao<SysUserEntity> {
    /**
     * 根据条件查询User
     *
     * @param user User
     * @return 符合条件列表
     */
    @Cacheable
    List<SysUserEntity> query(SysUserEntity user);

    /**
     * 获取该用户的权限
     *
     * @param id 用户ID
     * @return 权限列表
     */
    List<String> queryPermsById(Long id);

    /**
     * 根据用户名获取该用户的信息
     *
     * @param username 用户名
     * @return 用户
     */
    SysUserEntity queryByUsername(String username);
}
