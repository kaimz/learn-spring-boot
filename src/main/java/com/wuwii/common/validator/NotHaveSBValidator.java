package com.wuwii.common.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/2 22:29</pre>
 */
public class NotHaveSBValidator implements ConstraintValidator<NotHaveSB, String> {
    @Override
    public void initialize(NotHaveSB notHaveSB) {

    }

    /**
     * @param s                          待检验对象
     * @param constraintValidatorContext 检验上下文，可以设置检验的错误信息
     * @return false 代表检验失败
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !StringUtils.isNotBlank(s) || !s.toLowerCase().contains("sb");
    }
}
