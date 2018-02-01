package com.wuwii.module.sys.dao;

import com.wuwii.module.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {
    /**
     * 根据条件查询User
     *
     * @param user User
     * @return 符合条件列表
     */
    List<SysUserEntity> query(SysUserEntity user);
}
