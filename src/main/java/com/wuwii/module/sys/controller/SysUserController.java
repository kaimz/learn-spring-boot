package com.wuwii.module.sys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wuwii.module.sys.entity.SysUserEntity;
import com.wuwii.module.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<List<SysUserEntity>> query() {
        return new ResponseEntity<>(sysUserService.query(new SysUserEntity()), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SysUserEntity>> queryByPage() {
        return new ResponseEntity<>(sysUserService.queryByPage(new SysUserEntity()), HttpStatus.OK);
    }

    @GetMapping("/pageinfo")
    public ResponseEntity<PageInfo<SysUserEntity>> queryByPageInfo() {
        return new ResponseEntity<>(sysUserService.queryByPageInfo(new SysUserEntity()), HttpStatus.OK);
    }

    @PostMapping("/valid")
    public ResponseEntity<String> valid(@Validated @RequestBody SysUserEntity user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(500).body("校验失败");
        }
        return ResponseEntity.status(200).body("校验成功");
    }

}
