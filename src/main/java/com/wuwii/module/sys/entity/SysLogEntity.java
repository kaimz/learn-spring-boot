package com.wuwii.module.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/5 17:36</pre>
 */
@ApiModel("日志")
public class SysLogEntity implements Serializable {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("操作人")
    private String username;

}
