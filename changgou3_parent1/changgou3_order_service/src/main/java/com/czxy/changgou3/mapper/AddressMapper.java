package com.czxy.changgou3.mapper;

import com.czxy.changgou3.pojo.Address;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/30 0030
 **/
@org.apache.ibatis.annotations.Mapper
public interface AddressMapper extends Mapper<Address> {
    /**
     *修改用户默认地址
     * @param userId
     * @param defaultValue
     */
    @Select("update tb_address set isdefault =#{defaultValue} where user_id=#{userId}")
    void updateDefault(@Param("userId") Integer userId,@Param("defaultValue") Integer defaultValue);
}
