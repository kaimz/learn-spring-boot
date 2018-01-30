package com.wuwii.module.sys.service;

import com.wuwii.module.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysRoleService {

    SysRoleEntity queryObject(Long id);

    List<SysRoleEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRoleEntity sysRole);

    void update(SysRoleEntity sysRole);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
