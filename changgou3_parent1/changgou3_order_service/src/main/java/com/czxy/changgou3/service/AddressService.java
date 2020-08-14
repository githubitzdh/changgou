package com.czxy.changgou3.service;

import com.czxy.changgou3.mapper.AddressMapper;
import com.czxy.changgou3.pojo.Address;
import com.czxy.changgou3.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/30 0030
 **/
@Service
@Transactional
public class AddressService {
@Resource
    private AddressMapper addressMapper;

    /**
     * 根据用户id,查询指定用户的所有地址
     * @param id
     * @return
     */
    public List<Address> findAllByUserId(Integer id) {
        //1.拼接条件
        Example example = new Example(Address.class);
        Example.Criteria c = example.createCriteria();
         c.andEqualTo("userId",id);
         //2.查询
        List<Address> list = addressMapper.selectByExample(example);
        return list;

    }

    /**
     * 添加联系人
     * @param address
     */
    public void addAddress(User user,Address address) {
        //0.将之前的默认地址isDefalut更新为0
        addressMapper.updateDefault(user.getId(),0);
        //1.完善数据,
        // //1)用户
        address.setUserId(user.getId());
      //2)添加新地址,默认值1
        address.setIsdefault(1);
        addressMapper.insert(address);
    }
}
