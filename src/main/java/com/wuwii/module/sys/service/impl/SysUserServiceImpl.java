package com.wuwii.module.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuwii.module.sys.dao.SysUserDao;
import com.wuwii.module.sys.entity.SysUserEntity;
import com.wuwii.module.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    @Override
    public List<SysUserEntity> query(SysUserEntity user) {
        PageHelper.startPage(1, 2);
        return sysUserDao.query(user);
    }

    @Override
    public Page queryByPage(SysUserEntity user) {
        return PageHelper.startPage(1, 2).doSelectPage(() -> sysUserDao.query(user));
    }

    @Override
    public PageInfo queryByPageInfo(SysUserEntity user) {
        return PageHelper.startPage(1, 2).doSelectPageInfo(() -> sysUserDao.query(user));
    }

    @Override
    public SysUserEntity queryObject(Long id) {
        return sysUserDao.queryObject(id);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    public void save(SysUserEntity sysUser) {
        sysUserDao.save(sysUser);
    }

    @Override
    public void update(SysUserEntity sysUser) {
        sysUserDao.update(sysUser);
    }

    @Override
    public void delete(Long id) {
        sysUserDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysUserDao.deleteBatch(ids);
    }

}
