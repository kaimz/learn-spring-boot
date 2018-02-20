package com.wuwii.module.sys.common.util;

import com.wuwii.common.util.BaseEnum;

/**
 * 常量配置
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 13:01</pre>
 */
public class SysConstant {

    /**
     * 人员状态
     */
    public enum SysUserStatus implements BaseEnum<SysUserStatus, Integer> {
        /**
         * 账户已经激活（默认）
         */
        ACTIVE(1),
        /**
         * 账户锁定
         */
        LOCK(0);

        private Integer value;

        private SysUserStatus(Integer value) {
            this.value = value;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /**
     * 人员类型
     */
    public enum SysUserType implements BaseEnum<SysUserType, Integer> {
        /**
         * 普通用户
         */
        USER(1),
        /**
         * 系统管理员
         */
        ADMIN(0);

        private Integer value;

        private SysUserType(Integer value) {
            this.value = value;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }
}
