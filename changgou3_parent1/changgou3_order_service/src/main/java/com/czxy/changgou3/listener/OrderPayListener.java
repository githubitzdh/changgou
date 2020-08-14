package com.czxy.changgou3.listener;

import com.czxy.changgou3.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/14 0014
 **/
@Component
@RabbitListener(queues = "order_pay")
public class OrderPayListener {
  @Resource
  private OrderService orderService;
    @RabbitHandler
    public void  updateOrderStatus(String sn){
     orderService.updateOrderStatus(sn,"1");
    }

}
