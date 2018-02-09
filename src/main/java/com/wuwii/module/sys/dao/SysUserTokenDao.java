package com.wuwii.module.sys.dao;

import com.wuwii.module.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 16:01</pre>
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {

    /**
     * 根据 token 查询 token 对象
     *
     * @param token token
     * @return SysUserTokenEntity
     */
    SysUserTokenEntity queryByToken(String token);
}
