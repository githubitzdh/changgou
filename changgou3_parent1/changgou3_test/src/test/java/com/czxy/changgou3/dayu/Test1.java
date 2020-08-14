package com.czxy.changgou3.dayu;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.czxy.utils.SmsUtil2;
import org.junit.Test;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/26 0026
 **/

public class Test1 {
@Test
public void run1() throws ClientException {
    SendSmsResponse sendSmsResponse = SmsUtil2.sendSms("15012730793", "煌哥", "666", "冰山", "15012730793");
    //响应码
    System.out.println(sendSmsResponse.getCode());
    //响应信息
    System.out.println(sendSmsResponse.getMessage());
}

}
