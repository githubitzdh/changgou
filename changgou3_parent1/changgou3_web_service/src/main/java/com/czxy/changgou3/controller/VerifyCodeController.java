package com.czxy.changgou3.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/27 0027
 **/
@Controller
@RequestMapping("/verifycode")
public class VerifyCodeController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping
    public void verifyCode(String username, HttpServletResponse response) throws IOException {
        //1 绘制图片
        //字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
        String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

        int IMG_WIDTH = 72;
        int IMG_HEIGTH = 27;
        Random random = new Random();
        //创建图片
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGTH, BufferedImage.TYPE_INT_RGB);
        //画板
        Graphics g = image.getGraphics();
        //填充背景
        g.setColor(Color.WHITE);
        g.fillRect(1,1,IMG_WIDTH-2,IMG_HEIGTH-2);

        //设置字体
        g.setFont(new Font("楷体",Font.BOLD,25));

        /** #1 提供变量保存随机字符数据 */
        StringBuilder sb = new StringBuilder();

        //绘制4个字符
        for(int i = 1 ; i <= 4 ; i ++){
            //随机颜色
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            //随机生成4个字符
            int len = random.nextInt(VERIFY_CODES.length());
            String str = VERIFY_CODES.substring(len,len+1);

            /** #2 存放随机字符串 */
            sb.append( str );

            g.drawString(str, IMG_WIDTH / 6 * i , 22 );
        }
        /** #3 获得随机字符串 */
        String randomStr = sb.toString();
        System.out.println("图形验证码"+randomStr);
        /** #4 将结果保存到redis */
        String key = "login" + username;
        stringRedisTemplate.opsForValue().set(key, randomStr , 5 , TimeUnit.MINUTES);


        // 生成随机干扰线
        for (int i = 0; i < 30; i++) {
            //随机颜色
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            int x = random.nextInt(IMG_WIDTH - 1);
            int y = random.nextInt(IMG_HEIGTH - 1);
            int x1 = random.nextInt(12) + 1;
            int y1 = random.nextInt(6) + 1;
            g.drawLine(x, y, x - x1, y - y1);
        }


        //2 将图片响应给浏览器
        ImageIO.write(image,"jpeg", response.getOutputStream());
    }



}
