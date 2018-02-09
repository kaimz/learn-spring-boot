package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysUserRoleEntity;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysUserRoleService {

    SysUserRoleEntity queryObject(Long id);

    void save(SysUserRoleEntity sysUserRole);

    void update(SysUserRoleEntity sysUserRole);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
