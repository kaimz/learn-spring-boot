package com.wuwii.common.validator;

import com.wuwii.common.exception.KCException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类，校验不通过抛出自定义异常
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
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws KCException 校验不通过，则报KCException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws KCException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new KCException(msg.toString());
        }
    }
}
