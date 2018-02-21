package com.wuwii.module.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuwii.module.sys.common.util.ShiroUtils;
import com.wuwii.module.sys.common.util.SysConstant;
import com.wuwii.module.sys.dao.SysUserDao;
import com.wuwii.module.sys.entity.SysUserEntity;
import com.wuwii.module.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
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
    public void save(SysUserEntity sysUser) {
        sysUser.setCreateDate(new Date());
        // 密码加密 方式很多，任选
       /* String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUser.setPassword(new Sha256Hash(sysUser.getPassword(), salt).toHex());*/

        String salt = ShiroUtils.generateSalt(20);
        sysUser.setPassword(ShiroUtils.encryptPassword("SHA-256", sysUser.getPassword(), salt));
        sysUser.setSalt(salt);
        sysUser.setUsername(sysUser.getEmail());
        sysUser.setStatus(SysConstant.SysUserStatus.ACTIVE);
        sysUser.setType(SysConstant.SysUserType.USER);
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

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public SysUserEntity queryByUsername(String username) {
        return sysUserDao.queryByUsername(username);
    }

}
