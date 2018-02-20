package com.wuwii.module.sys.entity;

import java.io.Serializable;


/**
 * 
 *
 * @author KronChan
 * @email k@wuwii.com
 * @date 2018-01-30 16:47:32
 */
public class SysMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;
	//目录名称
	private String menuName;
	//菜单URL
	private String menuUrl;
	//授权(多个用逗号分隔，如：user:list,user:create)
	private String perms;
	//菜单icon
	private String menuIcon;
	//父级目录，0是顶级
	private Long parentId;
	//菜单类型：  0：目录   1：菜单   2：按钮
	private Integer type;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置：目录名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * 获取：目录名称
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * 设置：菜单URL
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/**
	 * 获取：菜单URL
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	/**
	 * 设置：授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public void setPerms(String perms) {
		this.perms = perms;
	}
	/**
	 * 获取：授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public String getPerms() {
		return perms;
	}
	/**
	 * 设置：菜单icon
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	/**
	 * 获取：菜单icon
	 */
	public String getMenuIcon() {
		return menuIcon;
	}
	/**
	 * 设置：父级目录，0是顶级
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级目录，0是顶级
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：菜单类型：  0：目录   1：菜单   2：按钮
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：菜单类型：  0：目录   1：菜单   2：按钮
	 */
	public Integer getType() {
		return type;
	}
}
