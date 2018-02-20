package com.wuwii.common.config;

import com.wuwii.common.validator.custom.CustomValidHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;

/**
 * mvc 配置
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/12 16:20</pre>
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private CustomValidHandlerMethodArgumentResolver customValidHandlerMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CustomValidHandlerMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

}
