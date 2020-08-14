package com.czxy.changgou3.Bcrypt;

import com.czxy.utils.BCrypt;
import org.junit.Test;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/29 0029
 **/
public class test1 {
    @Test
    public void run1(){
        //加密  , BCrypt.hashpw("需要加密的内容");
        String hashpw = BCrypt.hashpw("1234");
        System.out.println(hashpw);

        //校验 BCrypt.checkpw("原始密码", "加密后的内容");
        String pw = "$2a$10$mlq29F4ha./SDHb2K1X./uy6jDBVsDbXv2FO/2p0E3VgLuONqVtz6";
        boolean checkpw = BCrypt.checkpw("1234", pw);
        System.out.println(checkpw);
    }
}
