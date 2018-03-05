package com.wuwii.common.log;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/5 17:18</pre>
 */
public @interface SysLog {

    /**
     * 描述记录到日志得值
     *
     * @return
     */
    String value() default "";
}
