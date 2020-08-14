package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.Impression;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/23 0023
 **/
@org.apache.ibatis.annotations.Mapper
public interface ImpressionMapper extends Mapper<Impression> {

    /**
     * 查询指定spu的所有印象
     * @param spuid
     * @return
     */
    @Select("select * from tb_impression where spu_id=#{spuid}")
@Results({
        @Result(property = "spuId",column = "spu_id"),
        @Result(property = "skuId",column = "sku_id"),
})
    List<Impression> findImpressionsBySpuid(@Param("spuid") Integer spuid);
}
