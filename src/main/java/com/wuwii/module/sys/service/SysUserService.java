package com.wuwii.module.sys.service;

import com.github.pagehelper.Page;
import com.wuwii.module.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysUserService {

    Page query(SysUserEntity user);

    SysUserEntity queryObject(Long id);

    List<SysUserEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysUserEntity sysUser);

    void update(SysUserEntity sysUser);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
