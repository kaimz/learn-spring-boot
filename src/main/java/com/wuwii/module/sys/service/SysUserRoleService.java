package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysUserRoleService {

    SysUserRoleEntity queryObject(Long id);

    List<SysUserRoleEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysUserRoleEntity sysUserRole);

    void update(SysUserRoleEntity sysUserRole);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
