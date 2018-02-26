package com.wuwii.module.sys.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.wuwii.common.exception.KCException;
import com.wuwii.module.sys.common.util.JwtUtils;
import com.wuwii.module.sys.common.util.ShiroUtils;
import com.wuwii.module.sys.common.util.SysConstant;
import com.wuwii.module.sys.form.SysUserLoginForm;
import com.wuwii.module.sys.service.SysUserService;
import com.wuwii.module.sys.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/8 18:11</pre>
 */
@RestController
@Api("用户登陆")
@RequestMapping("/sys")
public class SysLoginController extends BaseController {
    @Resource
    private Producer producer;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserTokenService sysUserTokenService;
    @Resource
    private JwtUtils jwtUtils;

    /**
     * 获取验证码
     */
    @ApiOperation("获取登陆验证码")
    @GetMapping(value = "/captcha.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> captcha() throws IOException {
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", out);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        HttpHeaders headers = new HttpHeaders();
        //headers.set(HttpHeaders.SET_COOKIE2, text);
        headers.setCacheControl("no-store, no-cache");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(out.toByteArray());
    }

    @PostMapping("/login")
    @ApiOperation("系统登陆")
    public ResponseEntity<String> login(@RequestBody SysUserLoginForm userForm) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!userForm.getCaptcha().equalsIgnoreCase(kaptcha)) {
            throw new KCException("验证码不正确！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userForm.getUsername(), userForm.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);

        //账号锁定
        if (getUser().getStatus() == SysConstant.SysUserStatus.LOCK) {
            throw new KCException("账号已被锁定,请联系管理员");
        }
        return ResponseEntity.status(HttpStatus.OK).body(jwtUtils.generateToken(getUserId()));
    }
    /*@RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println(exception);
        return null;
    }*/
/*    @GetMapping("/logout")
    @ApiOperation("退出系统")
    public ResponseEntity<String> logout() {
        return ResponseEntity.status(HttpStatus.OK).body("成功退出！");
    }*/

    /*@GetMapping("/{html:((?!\\.html$).)*$}")
    public String html(@PathVariable String html) {
        return html;
    }*/

}
