package com.wuwii.module.sys.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //用户名

    private String username;
    //密码
    private String password;
    //手机号
    private String mobile;
    //邮件
    private String email;
    //创建者
    private Long createUserId;
    //创建时间
    private Date createDate;

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：创建者
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：创建者
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
