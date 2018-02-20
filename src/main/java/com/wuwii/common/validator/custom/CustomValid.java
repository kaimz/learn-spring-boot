package com.wuwii.common.validator.custom;

import java.lang.annotation.*;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/12 22:52</pre>
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValid {
    /**
     * 组名
     */
    Class<?>[] group() default {};
}
