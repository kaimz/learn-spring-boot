package com.wuwii.common.util;

/**
 * 通用枚举接口，保持枚举的一致
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 17:49</pre>
 */
public interface BaseEnum<E extends Enum<?>, T> {
    /**
     * 获取枚举的值
     * @return 枚举的值
     */
    T getValue();
}
