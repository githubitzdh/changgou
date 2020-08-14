package com.czxy.changgou.controller;

import com.czxy.changgou.service.PayService;
import com.czxy.changgou.utils.PayState;
import com.czxy.changgou3.vo.PayRequest;
import com.czxy.vo.BaseResult;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/11 0011
 **/
@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private PayService payService;
@Resource
private RabbitTemplate rabbitTemplate;
    /**
     * 通过订单号，获得微信支付链接
     *
     * @param payRequest
     * @return
     */
    @PostMapping
    public BaseResult payUrl(@RequestBody PayRequest payRequest) {
        //获得路径
        String payUrl = payService.payUrl(payRequest.getSn());
//返回
        return BaseResult.ok("成功获取").append("wxurl", payUrl);
    }


    @PostMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) {

        try {
            //1.处理请求
            //1.1以流的方式.获得请求体(请求参数)
            ServletInputStream is = request.getInputStream();
            //1.2将流转换成字符串 "<xml></xml>"
            String xmlStr = IOUtils.toString(is, "UTF-8");
            //1.3将字符串转换map
            Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);


            //2.获得订单号,修改状态
            //return_code: 返回状态码         result_code:业务结果
            if("SUCCESS".equals(map.get("return_code"))&&"SUCCESS".equals(map.get("result_code"))){
                String sn=map.get("out_trade_no");
                System.out.println(sn);


            //2.2 将订单号存放到RabbitMQ的order_pay队列中
                rabbitTemplate.convertAndSend("","order_pay",sn);
     //2.3将订单号存放到RabbitMQ的order_pay_auto队列汇总,完成推送功能
                rabbitTemplate.convertAndSend("","order_pay_auto",sn);

                //3.响应规定格式的内容
                //3.1响应类型
                response.setContentType("text/html");
                //3.2响应内容
                response.getWriter().print("<xml>\n" +
                        "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                        "   <return_msg><![CDATA[OK]]></return_msg></xml>");
            }else{
                System.out.println("错误了");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询订单支付状态
     * @param sn
     * @return测试可用:sn=1260516835070185500
     */
    @GetMapping("/{sn}")
    public BaseResult patState(@PathVariable("sn")Long sn){
    //查询
       PayState payState= payService.paystate(sn);
      //判断返回结果
       if(payState.getCode()==1){
           //成功
           return BaseResult.ok(payState.getDesc());
       }
       //错误
       return BaseResult.error(payState.getDesc());
    }


}
