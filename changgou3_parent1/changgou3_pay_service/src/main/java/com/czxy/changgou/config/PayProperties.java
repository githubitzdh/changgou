package com.czxy.changgou.config;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.InputStream;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/5/11 0011
 **/
@Data
@ConfigurationProperties(prefix="sc.pay")
public class PayProperties implements WXPayConfig {

    private String appID;               // 公众账号ID

    private String mchID;               // 商户号

    private String key;                 // 生成签名的密钥

    private int httpConnectTimeoutMs;   // 连接超时时间

    private int httpReadTimeoutMs;      // 读取超时时间

    @Override
    public InputStream getCertStream() {
        //加载证书，需要通过账号中心生成
        return null;
    }

    private String notifyUrl; //回调程序的地址

}
