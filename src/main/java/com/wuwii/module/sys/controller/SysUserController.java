package com.wuwii.module.sys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wuwii.common.validator.custom.CustomValid;
import com.wuwii.common.validator.custom.ValidatorUtils;
import com.wuwii.module.sys.entity.SysUserEntity;
import com.wuwii.module.sys.form.SysUserAddForm;
import com.wuwii.module.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
@RestController
@RequestMapping("/sys/user")
@Api("用户管理")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation("用于测试，查询")
    @ApiImplicitParam(name = "string", value = "id", dataType = "string")
    @RequiresPermissions("sys:user:list")
    @GetMapping()
    public ResponseEntity<List<SysUserEntity>> query(@CustomValid String string) {
        return new ResponseEntity<>(sysUserService.query(new SysUserEntity()), OK);
    }

    @GetMapping("/page")
    @ApiOperation("用于测试，分页")
    @RequiresPermissions("list")
    public ResponseEntity<Page<SysUserEntity>> queryByPage() {
        return new ResponseEntity<>(sysUserService.queryByPage(new SysUserEntity()), OK);
    }

    @GetMapping("/pageinfo")
    @ApiOperation("用于测试，分页")
    public ResponseEntity<PageInfo<SysUserEntity>> queryByPageInfo() {
        return new ResponseEntity<>(sysUserService.queryByPageInfo(new SysUserEntity()), OK);
    }

    @PostMapping("/valid")
    @ApiOperation("用于测试，参数校验")
    public ResponseEntity<String> valid(@Validated @RequestBody SysUserEntity user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(BAD_REQUEST).body("校验失败");
        }
        return ResponseEntity.status(OK).body("校验成功");
    }

    @PostMapping("/valid1")
    @ApiOperation("用于测试，参数校验")
    public ResponseEntity<String> customValid(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user);
        return ResponseEntity.status(OK).body("校验成功");
    }


    //@RequiresPermissions("sys:user:add")
    @ApiOperation("新增")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "SysUserAddForm", paramType = "body")
    @PostMapping()
    public ResponseEntity insert(@Validated @RequestBody SysUserAddForm user) {
        SysUserEntity userEntity = new SysUserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setCreateUserId(getUserId());
        sysUserService.save(userEntity);
        return ResponseEntity.status(CREATED).body("新增成功");
    }

}
