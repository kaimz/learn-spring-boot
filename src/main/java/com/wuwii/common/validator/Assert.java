package com.wuwii.common.validator;

import com.wuwii.common.exception.KCException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/1 8:34</pre>
 */
public class Assert {

    /**
     * check string isBlank，如果校验失败，抛出自定义异常，
     *
     * @param string  待校验的字符串
     * @param message 校验失败的信息
     */
    public static void isBlank(String string, String message) {
        if (StringUtils.isBlank(string)) {
            throw new KCException(message);
        }
    }

    /**
     * check string isEmpty，如果校验失败，抛出自定义异常，
     *
     * @param string  待校验的字符串
     * @param message 校验失败的信息
     */
    public static void isEmpty(String string, String message) {
        if (StringUtils.isEmpty(string)) {
            throw new KCException(message);
        }
    }

}
