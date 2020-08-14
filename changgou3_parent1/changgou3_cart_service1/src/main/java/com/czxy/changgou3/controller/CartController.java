package com.czxy.changgou3.controller;

import com.czxy.changgou3.cart.Cart;
import com.czxy.changgou3.config.JwtProperties;
import com.czxy.changgou3.pojo.User;
import com.czxy.changgou3.service.CartService;
import com.czxy.changgou3.vo.CartVo;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/27 0027
 **/
@RestController
@RequestMapping("/carts")
public class CartController {
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private HttpServletRequest request;
    @Resource
    private CartService cartService;

    /**
     * 添加购物车
     *
     * @param cartVo
     * @return
     */
    @PostMapping
    public BaseResult addCart(@RequestBody CartVo cartVo) {
        //1 获得用户信息
        User loginer = null;
        // 1.1 获得token
        try {
            String token = request.getHeader("Authorization");
            // 1.2 解析token
            loginer = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token无效");
        }

        //2.添加购物车
        cartService.addCart(loginer, cartVo);

        //3.返回
        return BaseResult.ok("添加成功");
    }

    /**
     * 查询购物车
     *
     * @return
     */
    @GetMapping
    public BaseResult selectCart() {
        //1 获得用户信息
        User loginer = null;
        try {
            // 1.1 获得token
            String token = request.getHeader("Authorization");
            // 1.2 解析token
            loginer = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token无效");
        }
        //查询
        Cart cart = cartService.selectCart(loginer);
        //3.返回
        return BaseResult.ok("查询成功", cart.getData().values());

    }

    @PutMapping
    public BaseResult updateCart(@RequestBody List<CartVo> cartVoList) {
        //1 获得用户信息
        User loginer = null;
        // 1.1 获得token
        try {
            String token = request.getHeader("Authorization");
            // 1.2 解析token
            loginer = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token无效");
        }

        try {
            cartService.updateCart(loginer, cartVoList);
            return BaseResult.ok("更新成功");
        } catch (Exception e) {
            return BaseResult.error("更新失败");
        }

    }
}

