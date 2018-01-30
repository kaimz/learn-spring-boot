package com.wuwii.module.sys.controller;

import com.wuwii.common.util.PageUtils;
import com.wuwii.common.util.Query;
import com.wuwii.common.util.R;
import com.wuwii.module.sys.entity.SysMenuEntity;
import com.wuwii.module.sys.service.SysMenuService;
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
@RequestMapping("/sys/sysmenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:sysmenu:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysMenuEntity> sysMenuList = sysMenuService.queryList(query);
        int total = sysMenuService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysMenuList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:sysmenu:info")
    public ResponseEntity<SysMenuEntity> info(@PathVariable("menuId") Long menuId) {
        SysMenuEntity sysMenu = sysMenuService.queryObject(menuId);

        return new ResponseEntity<SysMenuEntity>(sysMenu, HttpStatus.OK);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:sysmenu:save")
    public R save(@RequestBody SysMenuEntity sysMenu) {
        sysMenuService.save(sysMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:sysmenu:update")
    public R update(@RequestBody SysMenuEntity sysMenu) {
        sysMenuService.update(sysMenu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:sysmenu:delete")
    public R delete(@RequestBody Long[] menuIds) {
        sysMenuService.deleteBatch(menuIds);

        return R.ok();
    }

}
