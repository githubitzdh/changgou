package com.czxy.changgou3.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.czxy.changgou3.pojo.User;
import com.czxy.utils.SmsUtil2;
import com.czxy.vo.BaseResult;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/25 0025
 **/
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

   @PostMapping
    public BaseResult sendSms(@RequestBody User user){

       try {
           //1 随机生成长度为4数字字符串
           String code = RandomStringUtils.randomNumeric(4);
           System.out.println("验证码:"+code);

           //2 将随机值，保存到redis
           String key="sms_register"+user.getMobile();
           stringRedisTemplate.opsForValue().set(key,code,5, TimeUnit.MINUTES);

           //3 将随机值，以短信的方式发送给用户
           //SendSmsResponse sendSmsResponse = SmsUtil.sendSms(user.getMobile(), code, "");
           SendSmsResponse sendSmsResponse = SmsUtil2.sendSms(user.getMobile(), user.getUsername(),code,"","");

           //4 短信提示 OK 表示成功
           if("OK".equals(sendSmsResponse.getCode())){
               //发送成功
               return BaseResult.ok("发送成功");
           }else {
               //给出错误提示信息
               return  BaseResult.error(sendSmsResponse.getMessage());
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }



}
