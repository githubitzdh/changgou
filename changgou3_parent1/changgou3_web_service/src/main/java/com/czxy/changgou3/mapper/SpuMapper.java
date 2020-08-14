package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.Spu;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/15 0015
 **/
@org.apache.ibatis.annotations.Mapper
public interface SpuMapper  extends Mapper<Spu> {

    /**
     * 通过id查询spu详情,同时查询对应品牌
     * @param spuId
     * @return
     */
    @Select("select * from tb_spu where id=#{spuId}")
   @Results({
           @Result(property = "id",column = "id"),
           @Result(property = "spuName",column = "spu_name"),
           @Result(property = "spuSubname",column = "spu_subname"),
           @Result(property = "logo",column = "logo"),
           @Result(property = "cat1Id",column = "cat1_id"),
           @Result(property = "cat2Id",column = "cat2_id"),
           @Result(property = "cat3Id",column = "cat3_id"),
           @Result(property = "brandId",column = "brand_id"),
           @Result(property = "checkTime",column = "check_time"),
           @Result(property = "checkStatus",column = "check_status"),
           @Result(property = "price",column = "price"),
           @Result(property = "isOnSale",column = "is_on_sale"),
           @Result(property = "onSaleTime",column = "on_sale_time"),
           @Result(property = "deletedAt",column = "deleted_at"),
           @Result(property = "weight",column = "weight"),
           @Result(property = "description",column = "description"),
           @Result(property = "packages",column = "packages"),
           @Result(property = "aftersale",column = "aftersale"),
           @Result(property = "specList",column = "spec_list"),
           @Result(property = "createdAt",column = "created_at"),
           @Result(property = "updatedAt",column = "updated_at"),
           @Result(property = "brand",column = "brand_id",one = @One(select = "com.czxy.changgou3.mapper.BrandMapper.selectByPrimaryKey")),
   })
    Spu findSpuById(@Param("spuId") Integer spuId);

}
