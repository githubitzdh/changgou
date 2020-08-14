package com.czxy.changgou3.weixin;

import com.github.wxpay.sdk.WXPay;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangsir@itcast.cn
 * SUCCESS—支付成功
 * REFUND—转入退款
 * NOTPAY—未支付
 * CLOSED—已关闭
 * REVOKED—已撤销（刷卡支付）
 * USERPAYING--用户支付中
 * PAYERROR--支付失败
 * @date 2020/5/11 0011
 **/
public class TestWXPayOrderQuery {
    public static void main(String[] args) throws Exception {
        //1.准备配置类
        MyWXPayConfig payConfig = new MyWXPayConfig();
        //2.准备核心配置类
        WXPay wxPay = new WXPay(payConfig);
        //3准备参数
        Map<String, String> map = new HashMap();
        map.put("out_trade_no", "202010001225023");         //商户订单号

        //4.查询
        Map<String, String> result = wxPay.orderQuery(map);
        //5.处理结果
        System.out.println("返回状态码" + result.get("return_code"));
        System.out.println("返回信息" + result.get("return_msg"));
        System.out.println("业务结果" + result.get("result_code"));
        System.out.println("交易状态" + result.get("trade_state"));
        System.out.println("交易状态描述：" + result.get("trade_state_desc"));
        System.out.println(result);
    }
}
