package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.common.util.SysConstant;
import com.wuwii.module.sys.dao.SysMenuDao;
import com.wuwii.module.sys.dao.SysUserDao;
import com.wuwii.module.sys.dao.SysUserTokenDao;
import com.wuwii.module.sys.entity.SysMenuEntity;
import com.wuwii.module.sys.entity.SysUserEntity;
import com.wuwii.module.sys.entity.SysUserTokenEntity;
import com.wuwii.module.sys.service.ShiroService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 15:32</pre>
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Resource
    private SysUserTokenDao sysUserTokenDao;
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SysMenuDao sysMenuDao;

    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return 权限
     */
    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
        SysUserEntity user = sysUserDao.queryObject(userId);
        // 如果是管理员， 读取所有的权限列表
        if (user.getType() == SysConstant.SysUserType.ADMIN) {
            List<SysMenuEntity> menus = sysMenuDao.queryList(null);
            permsList = new ArrayList<>(menus.size());
            menus.forEach(m -> {
                permsList.add(m.getPerms());
            });
        } else {
            permsList = sysUserDao.queryPermsById(userId);
        }
        Set<String> permSet = new HashSet<>();
        for (String p : permsList) {
            if (p != null) {
                permSet.addAll(Arrays.asList(p.split(",")));
            }
        }
        return permSet;
    }

    /**
     * 根据 token 查询 token 实体类
     *
     * @param token token
     * @return SysUserTokenEntity
     */
    @Override
    public SysUserTokenEntity getTokenObjectByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SysUserEntity getUserById(long userId) {
        return sysUserDao.queryObject(userId);
    }
}
