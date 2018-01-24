package com.wuwii.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/1/17 17:57</pre>
 */
@RestController
@ConfigurationProperties(prefix = "com.wuwii.petcontroller")
public class PetController {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PetController.class);

    private String no;
    private String kind;
    private String name;

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
