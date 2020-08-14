package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/7 0007
 **/
@Mapper
public interface BrandMapper extends tk.mybatis.mapper.common.Mapper<Brand> {


    @Select("select b.* from tb_brand b,tb_category_brand cb where b.id=cb.brand_id and cb.category_id=#{categoryId}")
    @Results({
            @Result(property = "brandName",column = "brand_name"),
            @Result(property = "id",column = "id"),
            @Result(property = "logo",column = "logo"),

    })
    List<Brand> findAll(@Param("categoryId") Integer categoryId);

}
