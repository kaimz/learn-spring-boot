package com.wuwii.controller;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/1/17 17:57</pre>
 */
@RestController
@ConfigurationProperties(prefix = "com.wuwii.petcontroller")
@Api(value = "API - PetController", description = "这是一个控制器的描述 ")
public class PetController {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PetController.class);

    private String no;
    private String kind;
    private String name;

    @ApiOperation(value = "测试接口", notes = "测试接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "pet", value = "宠物", required = true, dataType = "PetController")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求完成"),
            @ApiResponse(code = 400, message = "请求参数错误")
    })
    @RequestMapping(path = "/index/{id}", method = RequestMethod.PUT)
    public PetController index1(@PathVariable("id") String id, @RequestBody PetController pet) {
        return pet;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
