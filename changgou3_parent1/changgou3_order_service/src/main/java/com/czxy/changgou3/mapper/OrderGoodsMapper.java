package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.OrderGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/4 0004
 **/
@org.apache.ibatis.annotations.Mapper
public interface OrderGoodsMapper extends Mapper<OrderGoods> {

    /**
     * 修改订单的状态
     * @param sn
     * @param status
     */
    @Update("update tb_order set status =#{status} where sn=#{sn}")
    void updateOrderStatus(@Param("sn") String sn,@Param("status") String status);
}
