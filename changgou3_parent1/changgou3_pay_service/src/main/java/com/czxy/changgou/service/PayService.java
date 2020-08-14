package com.czxy.changgou.service;

import com.czxy.changgou.utils.PayHelper;
import com.czxy.changgou.utils.PayState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/11 0011
 **/
@Service
public class PayService {
@Resource
    private PayHelper payHelper;

    /**
     * 通过订单号sn,获得微信支付链接
     * @param sn
     * @return
     */
    public String payUrl(Long sn){
    //通过工具类获得
     String payurl= payHelper.createPayUrl(sn);
     return payurl;
}

    /**
     * 根据订单号查询订单状态
     * @param sn
     * @return
     */
    public PayState paystate(Long sn) {
        //通过工具类进行查询
        PayState payState = payHelper.queryOrder(sn);
        return payState;

    }
}
