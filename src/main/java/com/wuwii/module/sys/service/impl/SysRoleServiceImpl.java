package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.dao.SysRoleDao;
import com.wuwii.module.sys.entity.SysRoleEntity;
import com.wuwii.module.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public SysRoleEntity queryObject(Long id) {
        return sysRoleDao.queryObject(id);
    }

    @Override
    public List<SysRoleEntity> queryList(Map<String, Object> map) {
        return sysRoleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleDao.queryTotal(map);
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
