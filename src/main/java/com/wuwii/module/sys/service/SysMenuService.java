package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysMenuService {

    SysMenuEntity queryObject(Long menuId);

    List<SysMenuEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysMenuEntity sysMenu);

    void update(SysMenuEntity sysMenu);

    void delete(Long menuId);

    void deleteBatch(Long[] menuIds);
}
