package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.autho2.TokenGenerator;
import com.wuwii.module.sys.dao.SysUserTokenDao;
import com.wuwii.module.sys.entity.SysUserTokenEntity;
import com.wuwii.module.sys.service.SysUserTokenService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/12 11:01</pre>
 */
@Service
@ConfigurationProperties(prefix = "sys.user-token")
public class SysUserTokenServiceImpl implements SysUserTokenService {
    /**
     * 有效时间，在系统配置文件配置
     */
    private int expire;

    @Resource
    private SysUserTokenDao userTokenDao;

    /**
     * 如果该用户没有 token记录，则给该用户生成一个 token
     * 如果存在 token 记录， 则 更新 token 和有效时间
     *
     * @param userId 用户id
     * @return token
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createToken(Long userId) {
        // 登陆的时候生成一个 token
        String token = TokenGenerator.generateValue();
        // 设置 token 的失效时间
        Date now = new Date();
        Date expireDateTime = new Date(now.getTime() + expire);
        /*
         * 判断该用户是否存在 token
         *   存在 则新增
         *   存在 则更新 token 和 token 的时间
         */
        SysUserTokenEntity userToken = userTokenDao.queryObject(userId);
        if (userToken == null) {
            userToken = new SysUserTokenEntity();
            userToken.setId(userId);
            userToken.setExpireTime(expireDateTime);
            userToken.setUpdateTime(now);
            userToken.setToken(token);
            userTokenDao.save(userToken);
        } else {
            userToken.setExpireTime(expireDateTime);
            userToken.setUpdateTime(now);
            userToken.setToken(token);
            userTokenDao.update(userToken);
        }
        return token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
