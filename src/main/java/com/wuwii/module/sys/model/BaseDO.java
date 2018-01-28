package com.wuwii.module.sys.model;

import java.io.Serializable;

/**
 * 基础实体，拥有主键
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/1/26 16:28</pre>
 */
public class BaseDO implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
