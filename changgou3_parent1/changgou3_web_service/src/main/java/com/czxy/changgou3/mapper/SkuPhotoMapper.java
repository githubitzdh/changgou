package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.SkuPhoto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/21 0021
 **/
@org.apache.ibatis.annotations.Mapper
public interface SkuPhotoMapper extends Mapper<SkuPhoto> {

    /**
     * 查询指定Skuid的所有图片
     * @param skuId
     * @return
     */
    @Select("select * from tb_sku_photo where sku_id =#{skuId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "skuId",column = "sku_id"),
            @Result(property = "url",column = "url"),
    })
    public List<SkuPhoto>findSkuPhotoBySkuId(@Param("skuId") Integer skuId);

}
