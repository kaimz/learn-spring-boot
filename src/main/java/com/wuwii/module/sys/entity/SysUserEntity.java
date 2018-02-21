package com.wuwii.module.sys.entity;

import com.wuwii.common.validator.group.AddGroup;
import com.wuwii.common.validator.group.UpdateGroup;
import com.wuwii.module.sys.common.util.SysConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty("主键")
    private Long id;
    //用户名
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    //密码
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    private String password;
    //手机号
    @ApiModelProperty("手机号")
    @Pattern(regexp = "^1([345789])\\d{9}$", message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String mobile;
    //邮箱
    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;
    //创建者
    private Long createUserId;
    //创建时间
    @DateTimeFormat
    @ApiModelProperty(value = "创建时间")
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

}
