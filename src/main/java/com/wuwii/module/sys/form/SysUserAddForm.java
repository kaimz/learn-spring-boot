package com.wuwii.module.sys.form;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增用户的表单
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/12 20:54</pre>
 */
public class SysUserAddForm {

    /**
     * 邮箱，本系统使用邮箱作为用户名
     */
    @Email(message = "邮箱格式不正确！")
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空！")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
