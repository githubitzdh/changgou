package com.czxy.changgou3.feign;

import com.czxy.changgou3.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/29 0029
 **/
@FeignClient(value = "cgwebservice",path = "/user")
public interface UserFeign {
    @PostMapping("/findByUsername")
    public User findByUsername(@RequestBody User user);


}
