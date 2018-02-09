package com.wuwii.module.sys.entity;

import java.util.Date;

/**
 * user token
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 15:45</pre>
 */
public class SysUserTokenEntity {
    /**
     * 主键 ，与 用户 id保持一致，保证一个用户只有一个ID
     */
    private Long id;

    /**
     * token
     */
    private String token;
    /**
     * token 过期时间
     */
    private Date expireTime;
    /**
     * 生成token的时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysUserTokenEntity{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", expireTime=" + expireTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
