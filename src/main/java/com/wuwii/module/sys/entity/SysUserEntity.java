package com.wuwii.module.sys.entity;

import com.wuwii.common.validator.group.AddGroup;
import com.wuwii.common.validator.group.UpdateGroup;
import com.wuwii.module.sys.common.SysConstant;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    //密码
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    private String password;
    //手机号
    @Pattern(regexp = "^1([345789])\\d{9}$", message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String mobile;
    //邮箱
    @Email(message = "邮箱格式不正确")
    private String email;
    //创建者
    private Long createUserId;
    //创建时间
    private Date createDate;
    /**
     * 密码加盐
     */
    private String salt;

    /**
     * 账号状态
     */
    private SysConstant.SysUserStatus status;

    /**
     * 账号类型（管理员还是用户）
     */
    private SysConstant.SysUserType type;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public SysConstant.SysUserStatus getStatus() {
        return status;
    }

    public void setStatus(SysConstant.SysUserStatus status) {
        this.status = status;
    }

    public SysConstant.SysUserType getType() {
        return type;
    }

    public void setType(SysConstant.SysUserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysUserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", createUserId=" + createUserId +
                ", createDate=" + createDate +
                ", salt='" + salt + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
