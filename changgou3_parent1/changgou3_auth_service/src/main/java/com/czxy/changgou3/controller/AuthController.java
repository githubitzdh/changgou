package com.czxy.changgou3.controller;

import com.czxy.changgou3.config.JWTProperties;
import com.czxy.changgou3.pojo.User;
import com.czxy.changgou3.service.AuthService;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/29 0029
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {
@Resource
    private AuthService authService;
@Resource
private StringRedisTemplate stringRedisTemplate;

@Resource
private JWTProperties jwtProperties;

@PostMapping("/login")
    public BaseResult login(@RequestBody User user){
   //1.验证码校验
    //1.1 获得redis中存放验证码数据
    String redisCode = stringRedisTemplate.opsForValue().get("login" + user.getUsername());
   //1.2为了保证一次性,将删除redis中
    stringRedisTemplate.delete("login"+user.getUsername());
    //1.3 redis中没有数据,无效验证码
    if(redisCode == null){
        return BaseResult.error("验证码无效");
    }
    //1.4 redis存放验证码和用户提交验证码不匹配:验证码错误
    if(!redisCode.equalsIgnoreCase(user.getCode())){
        return BaseResult.error("验证码错误");
    }
    //2.登录操作
    User loginer = authService.login(user);
    //3.提示
  if(loginer !=null){
      //生成token
      String token = JwtUtils.generateToken(loginer, jwtProperties.getExpire(), jwtProperties.getPrivateKey());


      //成功----将用户数据响应给浏览器
      System.out.println("登录成功");
      return BaseResult.ok("登录成功").append("user",loginer).append("token",token);
  }else{
      return BaseResult.error("用户名或密码不匹配");
  }

}
}
