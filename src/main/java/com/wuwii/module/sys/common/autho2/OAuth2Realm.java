package com.wuwii.module.sys.common.autho2;

import com.wuwii.module.sys.entity.SysUserEntity;
import com.wuwii.module.sys.service.ShiroService;
import com.wuwii.module.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

/**
 * 认证
 */
@CacheConfig(cacheNames = "realm")
public class OAuth2Realm extends AuthorizingRealm {
    @Resource
    private ShiroService shiroService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private CacheManager cacheManager;



    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     *
     * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
     * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
     * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
     * 缓存过期之后会再次执行。
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity) (principals.getPrimaryPrincipal());
        return getAuthorizationInfo(user.getId());
    }

    @Cacheable(key = "#p0")
    public AuthorizationInfo getAuthorizationInfo(Long userId) {
        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证回调函数,登录时调用
     * 首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
     * 如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
     * 交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        SysUserEntity user = sysUserService.queryByUsername(usernamePasswordToken.getUsername());
        //账号不存在、密码错误
        // 交给 shiro 自己去验证，
        // 明文验证
        return new SimpleAuthenticationInfo(
                user, // 存入凭证的信息，登陆成功后可以使用 SecurityUtils.getSubject().getPrincipal();在任何地方使用它
                Optional.ofNullable(user).map(SysUserEntity::getPassword).orElse(""),
                ByteSource.Util.bytes(Optional.ofNullable(user).map(SysUserEntity::getSalt).orElse("")),
                getName());

        // 加密的方式
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        /*return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), // 加盐，可以注册凭证匹配器 HashedCredentialsMatcher 告诉它怎么加密的
                getName());*/

    }
}
