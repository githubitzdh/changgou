package com.czxy.changgou3.weixin;

import com.github.wxpay.sdk.WXPay;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangsir@itcast.cn
 * @version 1.0
 * @date 2020/5/11 0011
 **/
public class TestWXPAYunifiedOrder {
    public static void main(String[] args) throws Exception {
        //下单过程
        //1/准备配置类
        MyWXPayConfig config = new MyWXPayConfig();

        //2/准备核心类
        WXPay wxPay = new WXPay(config);
        //3.准备参数
        Map<String, String> map = new HashMap<>();
        map.put("body", "第一个"); //商品描述
        map.put("out_trade_no", "202010001225023");//商户订单号
        map.put("total_fee", "111");  //标价金额
        map.put("spbill_create_ip", "127.0.0.1"); //终端IP
        map.put("notify_url", "http://www.baidu.com"); //通知地址
        map.put("trade_type", "NATIVE");//交易类型

        //4.调用方法
        Map<String, String> result = wxPay.unifiedOrder(map);

        //5.处理结果
        System.out.println("返回状态码" + result.get("return_code"));
        System.out.println("返回信息" + result.get("return_msg"));
        System.out.println("业务结果" + result.get("result_code"));
        System.out.println("交易类型" + result.get("trade_type"));
        System.out.println("二维码链接:"+result.get("code_url"));
        System.out.println(result);

    }
}
