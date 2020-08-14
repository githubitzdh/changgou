package com.czxy.changgou3.service;

import com.czxy.changgou3.mapper.UserMapper;
import com.czxy.changgou3.pojo.User;
import com.czxy.utils.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/22 0022
 **/
@Service
@Transactional
public class Uservice {
@Resource
 private UserMapper userMapper;

    /**
     * 通过用户名查询
     * @param username
     * @return
     */
    public User findByUsername(String username) {
    return  userMapper.findByUsername(username);
    }

    public User findByMobile(String mobile) {
       //1.拼凑条件
       Example example = new Example(User.class);
       Example.Criteria c = example.createCriteria();
    c.andEqualTo("mobile",mobile);
    //2.查询
       List<User> list = userMapper.selectByExample(example);
    if(list.size()==1){
       return list.get(0);
    }
    return null;
    }

    /**
     * 用户注册
     * @param user
     */
    public void register(User user) {
        //1.密码加密
       String newPassword  = BCrypt.hashpw(user.getPassword());
      user.setPassword(newPassword);

      //处理数据
       user.setCreatedAt(new Date());
       user.setUpdatedAt(user.getCreatedAt());
       userMapper.insert(user);
    }


}
