package com.czxy.changgou3.mapper;


import com.czxy.changgou3.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/22 0022
 **/
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
@Select("select * from tb_user where username=#{username}")
    public User findByUsername(@Param("username")String username);
}
