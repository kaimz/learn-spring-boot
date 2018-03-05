/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : learnboot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-05 21:18:36
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id`         BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username`   VARCHAR(50)         DEFAULT NULL
  COMMENT '操作用户',
  `method`     VARCHAR(255)        DEFAULT NULL
  COMMENT '方法名称',
  `params`     VARCHAR(255)        DEFAULT NULL
  COMMENT '方法参数',
  `time`       BIGINT(20)          DEFAULT NULL
  COMMENT '执行时间  ms',
  `ip`         VARCHAR(255)        DEFAULT NULL
  COMMENT 'ip',
  `createDate` DATETIME            DEFAULT NULL
  COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `menu_name` VARCHAR(50)         DEFAULT NULL
  COMMENT '目录名称',
  `menu_url`  VARCHAR(255)        DEFAULT NULL
  COMMENT '菜单URL',
  `perms`     VARCHAR(255)        DEFAULT NULL
  COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `menu_icon` VARCHAR(50)         DEFAULT NULL
  COMMENT '菜单icon',
  `parent_id` BIGINT(20)          DEFAULT NULL
  COMMENT '父级目录，0是顶级',
  `type`      INT(11)             DEFAULT NULL
  COMMENT '菜单类型：  0：目录   1：菜单   2：按钮',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', NULL, NULL, 'fa fa-cog', '0', '0');
INSERT INTO `sys_menu` VALUES ('2', '用户列表', 'module/sys/user.html', NULL, NULL, '1', '0');
INSERT INTO `sys_menu` VALUES ('3', '查看', NULL, 'sys:user:list,sys:user:info', NULL, '2', '0');
INSERT INTO `sys_menu` VALUES ('4', '新增', NULL, 'sys:user:add', NULL, '2', '0');
INSERT INTO `sys_menu` VALUES ('5', '删除', NULL, 'sys:user:delete', NULL, '2', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id`             BIGINT(20) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `role_name`      VARCHAR(100)        DEFAULT NULL
  COMMENT '角色名称',
  `create_user_id` BIGINT(20)          DEFAULT NULL
  COMMENT '创建者名称',
  `create_time`    DATETIME            DEFAULT NULL
  COMMENT '生成时间',
  `remark`         VARCHAR(255)        DEFAULT NULL
  COMMENT '备注',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id`      BIGINT(20) NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT(20)          DEFAULT NULL
  COMMENT '角色ID',
  `menu_id` BIGINT(20)          DEFAULT NULL
  COMMENT '菜单ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id`             BIGINT(20)  NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `username`       VARCHAR(50) NOT NULL
  COMMENT '用户名',
  `password`       VARCHAR(100)         DEFAULT NULL
  COMMENT '密码',
  `mobile`         VARCHAR(50)          DEFAULT NULL
  COMMENT '手机号',
  `email`          VARCHAR(100)         DEFAULT NULL
  COMMENT '邮件',
  `create_user_id` BIGINT(20)           DEFAULT NULL
  COMMENT '创建者',
  `create_date`    DATETIME             DEFAULT NULL
  COMMENT '创建时间',
  `salt`           VARCHAR(100)         DEFAULT NULL
  COMMENT '盐',
  `status`         TINYINT(1)  NOT NULL DEFAULT '1'
  COMMENT '0 为锁定，1为正常',
  `type`           TINYINT(1)  NOT NULL DEFAULT '1'
  COMMENT '1为普通用户，0为管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sy_username` (`username`) USING HASH
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES
  ('12', 'k@wuwii.com', '9656cab1c5b5ad49a44aae910de2efa25f31416f0016c84f5d3a1599cdb417fe', '18772383543',
   'k@wuwii.com', NULL, '2018-02-12 21:08:59', 'M8MQokzVPXiXgRh4hF0y', '1', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id`      BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20)          DEFAULT NULL
  COMMENT '用户ID',
  `role_id` BIGINT(20)          DEFAULT NULL
  COMMENT '角色ID',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `id`          BIGINT(20)   NOT NULL
  COMMENT '主键，与user的id相同',
  `token`       VARCHAR(100) NOT NULL
  COMMENT 'token',
  `expire_time` DATETIME DEFAULT NULL
  COMMENT '失效时间',
  `update_time` DATETIME DEFAULT NULL
  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_token` (`token`) USING HASH
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token`
VALUES ('12', 'd795da996ddee2f475cc42ea7bd66fbf', '2018-02-18 20:51:51', '2018-02-18 20:51:08');
