package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.dao.SysRoleDao;
import com.wuwii.module.sys.entity.SysRoleEntity;
import com.wuwii.module.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    @Override
    public SysRoleEntity queryObject(Long id) {
        return sysRoleDao.queryObject(id);
    }

    @Override
    public void save(SysRoleEntity sysRole) {
        sysRoleDao.save(sysRole);
    }

    @Override
    public void update(SysRoleEntity sysRole) {
        sysRoleDao.update(sysRole);
    }

    @Override
    public void delete(Long id) {
        sysRoleDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysRoleDao.deleteBatch(ids);
    }

}
