package com.wuwii.module.sys.controller;

import com.wuwii.common.util.PageUtils;
import com.wuwii.common.util.Query;
import com.wuwii.common.util.R;
import com.wuwii.module.sys.entity.SysUserRoleEntity;
import com.wuwii.module.sys.service.SysUserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
@RestController
@RequestMapping("/sys/sysuserrole")
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:sysuserrole:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysUserRoleEntity> sysUserRoleList = sysUserRoleService.queryList(query);
        int total = sysUserRoleService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysUserRoleList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:sysuserrole:info")
    public R info(@PathVariable("id") Long id) {
        SysUserRoleEntity sysUserRole = sysUserRoleService.queryObject(id);

        return R.ok().put("sysUserRole", sysUserRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:sysuserrole:save")
    public R save(@RequestBody SysUserRoleEntity sysUserRole) {
        sysUserRoleService.save(sysUserRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:sysuserrole:update")
    public R update(@RequestBody SysUserRoleEntity sysUserRole) {
        sysUserRoleService.update(sysUserRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:sysuserrole:delete")
    public R delete(@RequestBody Long[] ids) {
        sysUserRoleService.deleteBatch(ids);

        return R.ok();
    }

}
