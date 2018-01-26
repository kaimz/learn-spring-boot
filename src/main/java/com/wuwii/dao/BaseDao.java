package com.wuwii.dao;

import java.util.List;
import java.util.Map;

/**
 * dao层的基础接口
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/1/26 16:36</pre>
 */
public interface BaseDao<T> {
    /**
     * 保存单个对象
     *
     * @param t 对象
     */
    void save(T t);

    /**
     * 根据map的键值对，新增
     *
     * @param map map
     */
    void save(Map<String, Object> map);

    /**
     * 批量新增
     *
     * @param list 对象列表
     */
    void saveBatch(List<T> list);

    /**
     * 更新
     *
     * @param t 对象
     * @return 受影响行数
     */
    int update(T t);

    /**
     * 根据map键值对更新，必须有ID
     *
     * @param map map
     * @return 受影响行数
     */
    int update(Map<String, Object> map);

    /**
     * 根据id删除
     *
     * @param id id
     * @return 受影响行数
     */
    int delete(Object id);

    /**
     * 批量删除
     *
     * @param ids id的数据
     * @return 受影响行数
     */
    int deleteBatch(Object[] ids);

    /**
     * 根据ID查询
     *
     * @param id id
     * @return 查询出的实体对象，为null 表示不存在该ID
     */
    T queryObject(Object id);

    /**
     * 根据map条件查询
     *
     * @param map 条件
     * @return result list
     */
    List<T> queryList(Map<String, Object> map);


    /**
     * 根据条件查询总行数
     *
     * @param map 查询条件
     * @return 行数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询该表的总行数
     *
     * @return 总行数
     */
    int queryTotal();
}
