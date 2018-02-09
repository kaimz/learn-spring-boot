package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysRoleEntity;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysRoleService {

    SysRoleEntity queryObject(Long id);

    void save(SysRoleEntity sysRole);

    void update(SysRoleEntity sysRole);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
