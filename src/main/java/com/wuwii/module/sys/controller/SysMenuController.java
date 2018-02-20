package com.wuwii.module.sys.controller;

import com.wuwii.module.sys.service.SysMenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
@RestController
@RequestMapping("/sys/sysmenu")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;


}
