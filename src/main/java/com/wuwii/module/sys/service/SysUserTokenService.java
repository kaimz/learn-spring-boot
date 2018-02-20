package com.wuwii.module.sys.service;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/12 8:57</pre>
 */
public interface SysUserTokenService {
    /**
     * 如果该用户没有 token记录，则给该用户生成一个 token
     * 如果存在 token 记录， 则 更新 token 和有效时间
     *
     * @param userId 用户id
     * @return token
     */
    String createToken(Long userId);
}
