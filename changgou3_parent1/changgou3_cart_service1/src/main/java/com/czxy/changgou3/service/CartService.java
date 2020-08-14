package com.czxy.changgou3.service;


import com.alibaba.fastjson.JSON;
import com.czxy.changgou3.cart.Cart;
import com.czxy.changgou3.cart.CartItem;
import com.czxy.changgou3.feign.SKUFeign;
import com.czxy.changgou3.pojo.User;
import com.czxy.changgou3.vo.CartVo;
import com.czxy.changgou3.vo.OneSkuResult;
import com.czxy.vo.BaseResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/27 0027
 **/
@Service
@Transactional
public class CartService {
    @Resource
    private SKUFeign skuFeign;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void addCart(User loginer, CartVo cartVo) {
        //1.通过传来的skuid查询sku详情
        BaseResult<OneSkuResult> baseResult = skuFeign.findSkuById(cartVo.getSkuid());
        OneSkuResult oneSkuResult = baseResult.getData();

        //2.封装CartItem
        CartItem cartItem = new CartItem();
        cartItem.setSkuid(oneSkuResult.getSkuid());
        cartItem.setSpuid(oneSkuResult.getSpuid());
        cartItem.setGoodsName(oneSkuResult.getGoodsName());
        cartItem.setPrice(oneSkuResult.getPrice());
        cartItem.setCount(cartVo.getCount()); //传来数量
        cartItem.setChecked(cartVo.getChecked());
        cartItem.setMidlogo(oneSkuResult.getLogo().get("biglogo"));
        //转为map
        cartItem.setSpecInfoIdTxt(JSON.parseObject(oneSkuResult.getSpecInfo().get("id_txt"), Map.class));
        //3.从redis获得购物车,如果不存在需要创建一新的,如果存在(字符串->Java对象)
        String cartJSON = stringRedisTemplate.opsForValue().get("cart" + loginer.getId());
        Cart cart = null;
        //判断如果存在
        if (cartJSON != null) {
            //将字符创转换成java对象
            cart = JSON.parseObject(cartJSON, Cart.class);
        } else {
            //否则创建新购物车
            cart = new Cart();
        }
        //4.将封装数据添加到购物车
        cart.addToCart(cartItem);

        //把java对象转换回字符串
        cartJSON = JSON.toJSONString(cart);
        //5.将购物车保存redis中
        stringRedisTemplate.opsForValue().set("cart" + loginer.getId(), cartJSON);

    }

    /**
     * 查看购物车
     *
     * @param user
     * @return
     */
    public Cart selectCart(User user) {
        //查询当前用户的购物车
        String jsonstr = stringRedisTemplate.opsForValue().get("cart" + user.getId());
        //字符串转对象
        Cart cart = JSON.parseObject(jsonstr, Cart.class);
        //返回
        System.out.println(cart);
        return cart;

    }


    /**
     * 更新操作
     *
     * @param loginer
     * @param cartVoList
     */
    public void updateCart(User loginer, List<CartVo> cartVoList) {
        //如果提交的数据不在redis中,将删除
        //如果提交的数据在redis中,将更新数据

        //1.从redis获得购物车
        String cartStr = stringRedisTemplate.opsForValue().get("cart" + loginer.getId());
        //字符串转对象
        Cart cart = JSON.parseObject(cartStr, Cart.class);
        //如果购物车不存在,则抛出异常
        if (cart == null) {
            throw new RuntimeException("购物车不存在");
        }


        //2.处理请求数据,方便进行遍历,此处采用map进行处理
        HashMap<Integer, CartVo> map = new HashMap<>();
        for (CartVo cartVo : cartVoList) {
            map.put(cartVo.getSkuid(), cartVo);
        }

        //3.请求数据和redis中存放进行比对
        for (CartItem cartItem : cart.getData().values()) {
            //4.匹配成功,更新数据,不成功:删除数据
            CartVo cartvo = map.get(cartItem.getSkuid());
            if (cartvo!=null) {
                //更新操作
                cart.updataCart(cartvo.getSkuid(), cartvo.getCount(), cartvo.getChecked());
            } else {
                //删除
                cart.removeCart(cartvo.getSkuid());
            }

        }

        //5.更新购物车
        //java对象转字符串
        String jsonString = JSON.toJSONString(cart);
        //3 保存购物车
        stringRedisTemplate.opsForValue().set("cart" + loginer.getId(), jsonString);


    }
}
