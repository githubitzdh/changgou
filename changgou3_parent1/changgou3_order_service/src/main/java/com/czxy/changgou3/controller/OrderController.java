package com.czxy.changgou3.controller;

import com.czxy.changgou3.config.JwtProperties;
import com.czxy.changgou3.pojo.User;
import com.czxy.changgou3.service.OrderService;
import com.czxy.changgou3.vo.OrderVo;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/6 0006
 **/
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private JwtProperties jwtProperties;

    @PostMapping
    public BaseResult addOrder(@RequestBody OrderVo orderVo) {
        //1.登录用户
        User loginer = null;
        try {
            String token = request.getHeader("Authorization");
            loginer = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.ok("token无效");
        }
        //2.下订单
        Long sn = orderService.addOrder(loginer, orderVo);
        System.out.println(sn);
        //3.返回
        return BaseResult.ok("下单成功").append("sn", sn+"");
    }
}
