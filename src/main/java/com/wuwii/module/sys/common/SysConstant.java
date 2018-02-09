package com.wuwii.module.sys.common;

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
    public enum SysUserStatus implements BaseEnum<SysUserStatus, String> {
        /**
         * 账户已经激活（默认）
         */
        ACTIVE("1"),
        /**
         * 账户锁定
         */
        LOCK("0");

        private String value;

        private SysUserStatus(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    /**
     * 人员类型
     */
    public enum SysUserType implements BaseEnum<SysUserType, String> {
        /**
         * 普通用户
         */
        USER("1"),
        /**
         * 系统管理员
         */
        ADMIN("0");

        private String value;

        private SysUserType(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }
}
