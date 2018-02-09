package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysRoleMenuEntity;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysRoleMenuService {

    SysRoleMenuEntity queryObject(Long id);

    void save(SysRoleMenuEntity sysRoleMenu);

    void update(SysRoleMenuEntity sysRoleMenu);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
