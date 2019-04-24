package com.xfkj.mapper;

import com.xfkj.common.query.GenericQueryParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BaseMapper<T> {
    /**
     * 返回查询的结果集合
     * @return
     */
    List<T> selectList(T t);

    /**
     * 后台用、返回查询的结果集合
     * @param param 参数
     * @param rowBounds 页数
     * @return
     */
    List<T> selectByParamList(GenericQueryParam param, RowBounds rowBounds);
    /**
     * 数据库中的数据
     * @return
     */
    int countList(T t);

    /**
     * 后台用
     * @param param
     * @return
     */
    int selectByParamCount(GenericQueryParam param);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Integer id);
    int deleteByPrimaryKey(@Param("id") long id);
    int deleteByPrimaryKey(@Param("hash") String hash);

    /**
     * 插入
     * @param t
     * @return
     */
    int insertSelective(T t);
    int insert(T t);


    /**
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(@Param("id") Integer id);
    T selectByPrimaryKey(@Param("id") Long id);
    T  selectByPrimaryKey(@Param("hash") String hash);
    T  selectByPrimaryEntity(T t);

    /**
     * 修改
     * @param t
     * @return
     */
    int updateByPrimaryKeySelective(T t);
}
