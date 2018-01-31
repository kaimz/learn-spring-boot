package com.wuwii.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序,如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/1/31 18:39</pre>
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
