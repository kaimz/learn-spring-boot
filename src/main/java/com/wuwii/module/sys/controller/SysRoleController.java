package com.wuwii.module.sys.controller;

import com.wuwii.common.util.PageUtils;
import com.wuwii.common.util.Query;
import com.wuwii.common.util.R;
import com.wuwii.module.sys.entity.SysRoleEntity;
import com.wuwii.module.sys.service.SysRoleService;
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
@RequestMapping("/sys/sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:sysrole:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysRoleEntity> sysRoleList = sysRoleService.queryList(query);
        int total = sysRoleService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysRoleList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:sysrole:info")
    public R info(@PathVariable("id") Long id) {
        SysRoleEntity sysRole = sysRoleService.queryObject(id);

        return R.ok().put("sysRole", sysRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:sysrole:save")
    public R save(@RequestBody SysRoleEntity sysRole) {
        sysRoleService.save(sysRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:sysrole:update")
    public R update(@RequestBody SysRoleEntity sysRole) {
        sysRoleService.update(sysRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:sysrole:delete")
    public R delete(@RequestBody Long[] ids) {
        sysRoleService.deleteBatch(ids);

        return R.ok();
    }

}
