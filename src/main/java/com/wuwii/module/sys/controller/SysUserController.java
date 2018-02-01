package com.wuwii.module.sys.controller;

import com.github.pagehelper.Page;
import com.wuwii.common.util.PageUtils;
import com.wuwii.common.util.Query;
import com.wuwii.common.util.R;
import com.wuwii.module.sys.entity.SysUserEntity;
import com.wuwii.module.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
@RestController
@RequestMapping("/sys/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping()
    public ResponseEntity<Page<SysUserEntity>> query() {
        return new ResponseEntity<>(sysUserService.query(new SysUserEntity()), HttpStatus.OK);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:sysuser:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysUserEntity> sysUserList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysUserList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:sysuser:info")
    public R info(@PathVariable("id") Long id) {
        SysUserEntity sysUser = sysUserService.queryObject(id);

        return R.ok().put("sysUser", sysUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:sysuser:save")
    public R save(@RequestBody SysUserEntity sysUser) {
        sysUserService.save(sysUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:sysuser:update")
    public R update(@RequestBody SysUserEntity sysUser) {
        sysUserService.update(sysUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:sysuser:delete")
    public R delete(@RequestBody Long[] ids) {
        sysUserService.deleteBatch(ids);

        return R.ok();
    }

}
