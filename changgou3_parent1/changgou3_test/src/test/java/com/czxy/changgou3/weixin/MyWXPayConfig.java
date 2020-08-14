package com.czxy.changgou3.weixin;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * @author huangsir@itcast.cn
 * @version 1.0
 **/
public class MyWXPayConfig implements WXPayConfig {
    /**
     * 应用ID:微信开发平台审核通过的应用APPID
     * @return
     */
    @Override
    public String getAppID() {
        return "wx8397f8696b538317";
    }

    /**
     * 商户号:微信支付分配的商户号
     * @return
     */
    @Override
    public String getMchID() {
        return "1473426802";
    }

    /**
     * 秘钥,用于生成签名(sign)
     * @return
     */
    @Override
    public String getKey() {
        return "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    /**
     * 连接超时时间,单位是毫秒
     * @return
     */
    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    /**
     * 读超时时间,单位是毫秒
     * @return
     */
    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
