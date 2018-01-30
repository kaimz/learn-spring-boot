package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.dao.SysMenuDao;
import com.wuwii.module.sys.entity.SysMenuEntity;
import com.wuwii.module.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return sysMenuDao.queryObject(menuId);
    }

    @Override
    public List<SysMenuEntity> queryList(Map<String, Object> map) {
        return sysMenuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysMenuDao.queryTotal(map);
    }

    @Override
    public void save(SysMenuEntity sysMenu) {
        sysMenuDao.save(sysMenu);
    }

    @Override
    public void update(SysMenuEntity sysMenu) {
        sysMenuDao.update(sysMenu);
    }

    @Override
    public void delete(Long menuId) {
        sysMenuDao.delete(menuId);
    }

    @Override
    public void deleteBatch(Long[] menuIds) {
        sysMenuDao.deleteBatch(menuIds);
    }

}
