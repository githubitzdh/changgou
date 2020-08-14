package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.Sku;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/15 0015
 **/
@org.apache.ibatis.annotations.Mapper
public interface SkuMapper extends Mapper<Sku> {

    /**
     * 查询所有Sku,同时查询对应spu信息
     * @return
     */
    @Select("select * from tb_sku")
    @Results(id = "skuResult",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "stock",column = "stock"),
            @Result(property = "spuId",column = "spu_id"),
            @Result(property = "skuName",column = "sku_name"),
            @Result(property = "images",column = "images"),
            @Result(property = "price",column = "price"),
            @Result(property = "specInfoIdList",column = "spec_info_id_list"),
            @Result(property = "specInfoIdTxt",column = "spec_info_id_txt"),
            @Result(property = "spu",column = "spu_id",one = @One(select = "com.czxy.changgou3.mapper.SpuMapper.findSpuById")),
    })
    List<Sku> findAllSkus();


    /**
     * 通过spuid查询所有的sku
     * @param spuId
     * @return
     */
    @Select("select * from tb_sku where spu_id=#{spuId}")
   @ResultMap("skuResult")
    public List<Sku>findSkuBySpuId(@Param("spuId") Integer spuId);
}
