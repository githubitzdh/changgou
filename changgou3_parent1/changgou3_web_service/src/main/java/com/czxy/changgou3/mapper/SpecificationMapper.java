package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.Specification;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/7 0007
 **/
@org.apache.ibatis.annotations.Mapper
public interface SpecificationMapper extends Mapper<Specification> {

    @Select("select * from tb_specification where category_id = #{categoryId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "specName",column = "spec_name"),
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "options",column = "id",many = @Many(select = "com.czxy.changgou3.mapper.SpecificationOptionMapper.findAll")),
    })
    List<Specification> findall(@Param("categoryId") Integer categoryId);

}
