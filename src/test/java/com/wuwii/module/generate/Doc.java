package com.wuwii.module.generate;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;
import java.nio.file.Paths;

/**
 * 生成 adoc 文件，然后使用 swagger2markup 插件把它 转换成 html 文件
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/26 10:56</pre>
 */
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Doc {
    @Test
    public void testGenerateDoc() throws Exception {
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
