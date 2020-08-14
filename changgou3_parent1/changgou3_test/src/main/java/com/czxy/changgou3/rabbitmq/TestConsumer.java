package com.czxy.changgou3.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/13 0013
 **/
@Component  //将监听器交于spring容器
@RabbitListener(queues = "changgou_test")
public class TestConsumer {

    @RabbitHandler
    public void run1(String message){
        System.out.println("消费者dddd"+message);
    }
}
