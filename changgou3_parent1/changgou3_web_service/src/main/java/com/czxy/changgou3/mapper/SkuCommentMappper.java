package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.SkuComment;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/15 0015
 **/
@org.apache.ibatis.annotations.Mapper
public interface SkuCommentMappper extends Mapper<SkuComment> {

    /**
     * 根据spuid查询对应评论数
     * @param spuId
     * @return
     */
    @Select("select count(*) from tb_sku_comment where spu_id=#{spuId}")
   public Integer findNumBySpuId(@Param("spuId") Integer spuId);

    /**
     * 根据skuid查询对应评论数
     * @param skuId
     * @return
     */
    @Select("select count(*) from tb_sku_comment where sku_id=#{skuId}")
    public Integer findNumBySkuId(@Param("skuId") Integer skuId);

    /**
     * 查询sku评论五星平均数
     * @param skuId
     * @return
     */
    @Select("select AVG(star) from tb_sku_comment where sku_id=#{skuId}")
    public Integer findAvgStarBySkuId(@Param("skuId") Integer skuId);

    /**
     * 根据spuid, 查询好评度
     * @param spuid
     * @param
     * @return
     */
    @Select("select  count(*) from tb_sku_comment where spu_id =#{spuid} and ratio=#{ratio}")
    Integer findCommentCountByRatio(@Param("spuid") Integer spuid,@Param("ratio") Integer ratio);



    /**
     * 查询指定spu的所有评论
     * @param spuid
     * @return
     */
    @Select("select * from tb_sku_comment where spu_id = #{spuid}")
    @Results({
            @Result(property = "id" , column = "id"),
            @Result(property = "createdAt" , column = "created_at"),
            @Result(property = "updatedAt" , column = "updated_at"),
            @Result(property = "userId" , column = "user_id"),
            @Result(property = "spuId" , column = "spu_id"),
            @Result(property = "skuId" , column = "sku_id"),
            @Result(property = "ratio" , column = "ratio"),
            @Result(property = "specList" , column = "spec_list"),
            @Result(property = "content" , column = "content"),
            @Result(property = "star" , column = "star"),
            @Result(property = "isShow" , column = "isShow"),
            @Result(property = "sn" , column = "sn"),
            @Result(property = "user" , one = @One(select = "com.czxy.changgou3.mapper.UserMapper.selectByPrimaryKey"), column = "user_id"),
    })
    List<SkuComment> findCommentsBySpuid(@Param("spuid") Integer spuid);
}
