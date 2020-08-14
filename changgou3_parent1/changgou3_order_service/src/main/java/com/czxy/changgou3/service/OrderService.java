package com.czxy.changgou3.service;

import com.alibaba.fastjson.JSON;
import com.czxy.changgou3.cart.Cart;
import com.czxy.changgou3.cart.CartItem;
import com.czxy.changgou3.feign.SkuFeign;
import com.czxy.changgou3.mapper.AddressMapper;
import com.czxy.changgou3.mapper.OrderGoodsMapper;
import com.czxy.changgou3.mapper.OrderMapper;
import com.czxy.changgou3.pojo.Address;
import com.czxy.changgou3.pojo.Order;
import com.czxy.changgou3.pojo.OrderGoods;
import com.czxy.changgou3.pojo.User;
import com.czxy.changgou3.vo.OrderVo;
import com.czxy.utils.IdWorker;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;

/**
 * @author zhandehuang@itcast.cn
 * 下订单,准备订单对象,并封装对应数据,将订单对象保存即可
 **/
@Service
//@Transactional
public class OrderService {
    @Resource
    private IdWorker idWorker;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Resource
    private SkuFeign skuFeign;


    /**
     * 下订单,准备订单对象,并封装数据
     * @param loginer
     * @param orderVo
     * @return
     */
 @GlobalTransactional   //全局事务
    public Long addOrder(User loginer, OrderVo orderVo) {
        //1.创建订单对象
        Order order = new Order();

        //1.1 生成订单号(序列号:采用雪花算法)
        //生成orderID
        long sn = idWorker.nextId();
        order.setSn(sn);


        //1.2 封装收货人信息
        //获得前台传输过来的收货地址和收货人信息,生成订单,保存到数据库
        Address address = addressMapper.selectByPrimaryKey(orderVo.getAddressId());
        order.setShrName(address.getShrName());
        order.setShrMobile(address.getShrMobile());
        order.setShrProvince(address.getShrProvince());
        order.setShrCity(address.getShrCity());
        order.setShrArea(address.getShrArea());
        order.setShrAddress(address.getShrAddress());

        //1.3系统自动生成数据
        order.setCreatedAt(new Date());
        order.setUpdatedAt(order.getCreatedAt());
        //1.4设置状态;创建订单的时候,默认情况时未支付状态
        order.setStatus(0);     //(此处可用常量替换)

        //1.5 设置用户信息
        order.setUserId(loginer.getId());

        //2.获得购物车,根据用户id获得对应的购物车
        String cartStr = stringRedisTemplate.opsForValue().get("cart" + loginer.getId());
        //字符串转对象
        Cart cart = JSON.parseObject(cartStr, Cart.class);

        double total = 0;
        //2.1 保存处理购物车中已经勾选的商品信息,需要转换成OrderGoods
        //遍历购物项
        Iterator<CartItem> it = cart.getData().values().iterator();
        while (it.hasNext()) {
            CartItem cartItem = it.next();
            //选中
            if (cartItem.getChecked()) {
                //2.1.1.创建OrderGoods对象(将购物车中的商品的信息赋值给OrderGoods)
                OrderGoods orderGoods = new OrderGoods();
                //2.1.2填充数据;订单的序列号,商品信息
                orderGoods.setSn(sn);
                orderGoods.setSkuId(cartItem.getSkuid());
                orderGoods.setSpuId(cartItem.getSpuid());
                orderGoods.setNumber(cartItem.getCount());
                orderGoods.setSpecList(JSON.toJSONString(cartItem.getSpecInfoIdTxt()));
                orderGoods.setSkuName(cartItem.getGoodsName());
                orderGoods.setLogo(cartItem.getMidlogo());
                orderGoods.setPrice(cartItem.getPrice());

                //2.1.3.保存购物车中的商品信息
                orderGoodsMapper.insert(orderGoods);
                //2.1.4.远程调用方法,将该商品的数量减少,修改库存
                skuFeign.updateSkuNum(cartItem.getSkuid(), cartItem.getCount());

                //2.1.5.维护购物车数据, 购物车中移除该商品
                it.remove();
                //2.1.6 计算勾选商品的总价
                total += (cartItem.getCount() * cartItem.getPrice());

            }
        }


        //2.2 订单总价(从购物车获取) --所有勾选综合
        //order.setTotalPrice(total);
        order.setTotalPrice(total);

        //3.保存订单
        orderMapper.insert(order);


        //4.将购物车对象写入redis
        stringRedisTemplate.opsForValue().set("cart" + loginer.getId(), JSON.toJSONString(cart));
        //5. 将订但的序列号返回
        return sn;
    }

    /**
     * 修改指定订单号的状态
     * @param sn
     * @param status
     */
    public void updateOrderStatus(String sn, String status) {
   orderGoodsMapper.updateOrderStatus(sn,status);
    }
}
