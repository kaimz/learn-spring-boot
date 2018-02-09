package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysMenuEntity;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysMenuService {

    SysMenuEntity queryObject(Long menuId);

    void save(SysMenuEntity sysMenu);

    void update(SysMenuEntity sysMenu);

    void delete(Long menuId);

    void deleteBatch(Long[] menuIds);
}
