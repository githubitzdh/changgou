package com.czxy.changgou3.redis;

import redis.clients.jedis.Jedis;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/24 0024
 **/
public class Test1 {

    public static void main(String[] args) {
        //1.连接redis , new Jedis(ip地址i; 端口号)
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //2.设置数据, set(键,值)
        jedis.set("czxy", "chuagnzhi");

        //3.获得数据, get(键)
        String czxy = jedis.get("czxy");
        System.out.println(czxy);

        //4.释放资源
        jedis.close();

    }
}
