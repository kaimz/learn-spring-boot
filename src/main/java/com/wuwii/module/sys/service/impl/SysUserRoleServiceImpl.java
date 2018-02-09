package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.dao.SysUserRoleDao;
import com.wuwii.module.sys.entity.SysUserRoleEntity;
import com.wuwii.module.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public SysUserRoleEntity queryObject(Long id) {
        return sysUserRoleDao.queryObject(id);
    }

    @Override
    public void save(SysUserRoleEntity sysUserRole) {
        sysUserRoleDao.save(sysUserRole);
    }

    @Override
    public void update(SysUserRoleEntity sysUserRole) {
        sysUserRoleDao.update(sysUserRole);
    }

    @Override
    public void delete(Long id) {
        sysUserRoleDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysUserRoleDao.deleteBatch(ids);
    }

}
