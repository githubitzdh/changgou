package com.czxy.changgou3.controller;

import com.czxy.changgou3.config.JwtProperties;
import com.czxy.changgou3.pojo.Address;
import com.czxy.changgou3.pojo.User;
import com.czxy.changgou3.service.AddressService;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/30 0030
 **/
@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private AddressService addressService;
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private HttpServletRequest request;

    /**
     * 查看相应用户所有收货地址
     *
     * @return
     */
    @GetMapping
    public BaseResult selectAddress() {
        //1.获得登录用户
        User loginer = null;
        try {
            String token = request.getHeader("Authorization");
            loginer = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token无效");
        }
//2.通过用户查询所有收货地址
        List<Address> list = addressService.findAllByUserId(loginer.getId());

//3.返回
        return BaseResult.ok("查询成功", list);
    }

    /**
     * 添加联系人
     *
     * @param address
     * @return
     */
    @PostMapping
    public BaseResult addAddress(@RequestBody Address address) {
        //1.登录校验, 获得用户,token校验
        User loginer = null;
        try {
            //token校验权限
            String token = request.getHeader("Authorization");
            loginer =  JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token无效");
        }
        //保存
        addressService.addAddress(loginer, address);
        //返回
        return BaseResult.ok("添加成功");

    }


}
