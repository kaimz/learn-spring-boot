package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysRoleMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysRoleMenuService {

    SysRoleMenuEntity queryObject(Long id);

    List<SysRoleMenuEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRoleMenuEntity sysRoleMenu);

    void update(SysRoleMenuEntity sysRoleMenu);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
