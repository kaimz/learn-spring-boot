package com.wuwii.common.handle;

import com.wuwii.common.util.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * http://blog.csdn.net/fighterandknight/article/details/51520595
 * 解决 Mybatis 中枚举的问题，
 * 获取 ResultSet 的值都是获取字符串的，然后比较字符串，以便通用。
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/9 17:26</pre>
 */
public abstract class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {
    /**
     * 枚举的class
     */
    private Class<E> type;
    /**
     * 枚举的每个子类枚
     */
    private E[] enums;

    /**
     * 一定要有默认的构造函数，不然抛出 not found method 异常
     */
    public BaseEnumTypeHandler() {
    }

    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     *
     * @param type 配置文件中设置的转换类
     */
    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
                                    JdbcType jdbcType) throws SQLException {
        /*
         * BaseTypeHandler已经帮我们做了parameter的null判断
         * 数据库存储的是枚举的值，所以我们这里使用 value ， 如果需要存储 name，可以自定义修改
         */
        if (jdbcType == null) {
            ps.setString(i, Objects.toString(parameter.getValue()));
        } else {
            ps.setObject(i, parameter.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String i = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String i = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String i = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类 enums，让遍历更加高效快捷，
     * <p>
     * 我将取出来的值 全部转换成字符串 进行比较，
     *
     * @param value 数据库中存储的自定义value属性
     * @return value 对应的枚举类
     */
    private E locateEnumStatus(String value) {
        for (E e : enums) {
            if (Objects.toString(e.getValue()).equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + value + ",请核对"
                + type.getSimpleName());
    }

}
