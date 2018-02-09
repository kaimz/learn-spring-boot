package com.wuwii.module.sys.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wuwii.module.sys.entity.SysUserEntity;

import java.util.List;

/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public interface SysUserService {

    List query(SysUserEntity user);

    Page queryByPage(SysUserEntity user);

    PageInfo queryByPageInfo(SysUserEntity user);

    SysUserEntity queryObject(Long id);

    void save(SysUserEntity sysUser);

    void update(SysUserEntity sysUser);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
