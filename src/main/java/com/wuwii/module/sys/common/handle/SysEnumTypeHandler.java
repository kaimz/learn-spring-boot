package com.wuwii.module.sys.common.handle;

import com.wuwii.common.handle.BaseEnumTypeHandler;
import com.wuwii.common.util.BaseEnum;
import com.wuwii.module.sys.common.util.SysConstant;
import org.apache.ibatis.type.MappedTypes;

/**
 * http://blog.csdn.net/u014044812/article/details/78258730
 * 枚举转换的公共模块
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 18:12</pre>
 */
@MappedTypes(value = {SysConstant.SysUserStatus.class, SysConstant.SysUserType.class})
public class SysEnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseEnumTypeHandler<E> {
    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     *
     * @param type 配置文件中设置的转换类
     */
    public SysEnumTypeHandler(Class<E> type) {
        super(type);
    }
}
