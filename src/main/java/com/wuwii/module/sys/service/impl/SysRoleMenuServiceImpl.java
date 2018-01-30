package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.dao.SysRoleMenuDao;
import com.wuwii.module.sys.entity.SysRoleMenuEntity;
import com.wuwii.module.sys.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public SysRoleMenuEntity queryObject(Long id) {
        return sysRoleMenuDao.queryObject(id);
    }

    @Override
    public List<SysRoleMenuEntity> queryList(Map<String, Object> map) {
        return sysRoleMenuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleMenuDao.queryTotal(map);
    }

    @Override
    public void save(SysRoleMenuEntity sysRoleMenu) {
        sysRoleMenuDao.save(sysRoleMenu);
    }

    @Override
    public void update(SysRoleMenuEntity sysRoleMenu) {
        sysRoleMenuDao.update(sysRoleMenu);
    }

    @Override
    public void delete(Long id) {
        sysRoleMenuDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysRoleMenuDao.deleteBatch(ids);
    }

}
