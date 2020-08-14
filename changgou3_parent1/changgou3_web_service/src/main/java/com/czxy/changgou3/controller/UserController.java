package com.czxy.changgou3.controller;

import com.czxy.changgou3.pojo.User;
import com.czxy.changgou3.service.Uservice;
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
 * @date 2020/3/22 0022
 **/
@RestController
@RequestMapping("/user")
public class UserController {
@Resource
    private Uservice userService;
@Resource
private StringRedisTemplate stringRedisTemplate;

    /**
     * 校验用户名
     * @param user
     * @return
     */
    @PostMapping("/checkusername")
    public BaseResult checkusername(@RequestBody User user){
    System.out.println(user.getUsername());
    //查询用户
    User findUser=userService.findByUsername(user.getUsername());
    //判断
    if(findUser!=null){
        return BaseResult.error("用户名已经存在");
    }else {
        return BaseResult.ok("用户名可用");
    }
}

    /**
     * 校验手机号
     * @param user
     * @return
     */
    @PostMapping("/checkmobile")
    public BaseResult checkMobile(@RequestBody User user){
    //查询用户findByMobile
    User finduser=userService.findByMobile(user.getMobile());
    //判断
    if(finduser!=null){
        return  BaseResult.error("电话号码已经注册");
    }else {
        return BaseResult.ok("电话号码可用");
    }
}

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public BaseResult register(@RequestBody User user){
        //1.验证
    User finduser=userService.findByUsername(user.getUsername());
    //1.1. 用户名占用
    if(finduser!=null){
        return BaseResult.error("用户名已经存在");
    }
        //1.2 手机号占用
   finduser=userService.findByMobile(user.getMobile());
    if(finduser!=null){
        return BaseResult.error("电话号码已经存在");
    }

    //从redis获得验证码
    String rediscode=stringRedisTemplate.opsForValue().get("sms_register"+user.getMobile());
    //删除redis中的验证码(可等自动删除)
    //stringRedisTemplate.delete("sms_register"+user.getMobile());
        //1.3 验证码无效
    if(rediscode == null){
        return BaseResult.error("验证码失败");
    }
        //1.4 验证码错误
    if(! rediscode.equals(user.getCode())){
        return BaseResult.error("验证码错误");
    }

        //2.注册
     userService.register(user);
        //3.提示
    return BaseResult.ok("注册成功");

}


@PostMapping("/findByUsername")
    public User findByUsername(@RequestBody User user){
        //查询用户
     User findUser=userService.findByUsername(user.getUsername());
     return findUser;
}

}
