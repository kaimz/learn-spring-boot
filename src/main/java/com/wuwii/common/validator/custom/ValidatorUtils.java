package com.wuwii.common.validator.custom;

import com.wuwii.common.exception.KCException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * hibernate-validator校验工具类，校验不通过抛出自定义异常
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/1 8:34</pre>
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 手动校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws KCException 校验不通过，则抛出 KCException 异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws KCException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            String msg = constraintViolations.parallelStream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("，"));
            throw new KCException(msg);
        }
    }
}
