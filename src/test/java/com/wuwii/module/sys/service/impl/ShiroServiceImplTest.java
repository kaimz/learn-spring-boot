package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.service.ShiroService;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.URL;
import java.nio.file.Paths;

/**
 * ShiroServiceImpl Tester.
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>02/09/2018</pre>
 */
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ShiroServiceImplTest {
    @Resource
    private ShiroService shiroService;


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getUserPermissions(long userId)
     */
    @Test
    public void testGetUserPermissions() throws Exception {
        System.out.println(shiroService.getUserPermissions(1));
    }

    /**
     * Method: getTokenObjectByToken(String token)
     */
    @Test
    public void testGetTokenObjectByToken() throws Exception {
        System.out.println(shiroService.getTokenObjectByToken("1231231"));
    }

    /**
     * Method: getUserById(long userId)
     */
    @Test
    public void testGetUserById() throws Exception {
        // System.out.println(shiroService.getUserById(1));
        //  输出Asciidoc格式的文档  .adoc
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC) // 设置输出文件的语言：ASCIIDOC, MARKDOWN, CONFLUENCE_MARKUP
                //.withOutputLanguage(Language.ZH)  // 设置中文
                .withPathsGroupedBy(GroupBy.TAGS) // 设置目录的展现方式
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        // 读取配置文件的流，也可以读取外部的 Swagger api
        Swagger2MarkupConverter.from(new URL("http://localhost:8013/v2/api-docs")) // api 文件的文职
                .withConfig(config)
                .build()
                .toFile(Paths.get("/docs/asciidoc/generated/api-doc")); // 存储的位置
    }


} 
