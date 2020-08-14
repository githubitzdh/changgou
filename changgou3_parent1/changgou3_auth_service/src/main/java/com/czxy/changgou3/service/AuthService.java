package com.czxy.changgou3.service;

import com.czxy.changgou3.feign.UserFeign;
import com.czxy.changgou3.pojo.User;
import com.czxy.utils.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/29 0029
 **/
@Service
public class AuthService {
@Resource
    private UserFeign userFeign;

public User login(User user){
    //查询
    User findUser=userFeign.findByUsername(user);
    if(findUser == null){
        return null;
    }
    //校验密码是否正确
    boolean checkpw= BCrypt.checkpw(user.getPassword(),findUser.getPassword());
    if(checkpw){
        //登录成功
        return findUser;
    }else{
        return null;
    }

}
}
