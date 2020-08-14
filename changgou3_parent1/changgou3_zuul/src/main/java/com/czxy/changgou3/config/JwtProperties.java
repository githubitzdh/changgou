package com.czxy.changgou3.config;

import com.czxy.utils.RasUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/29 0029
 **/
@Data
@ConfigurationProperties(prefix = "sc.jwt")
@Component
public class JwtProperties {

    private String secret; // 密钥

    private String pubKeyPath;// 公钥

    private String priKeyPath;// 私钥

    private int expire;// token过期时间

    private PublicKey publicKey; // 公钥

    private PrivateKey privateKey; // 私钥

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    @PostConstruct
    public void init(){
        try {
            //    公钥/私钥的路径  -->公钥/私钥的对象
            //1 检验公钥/私钥存在:文件
            File pubFile = new File(this.pubKeyPath);
            File priFile = new File(this.priKeyPath);
            //2 检验公钥/私钥存在: 文件不存在,重新生产公钥和私钥
            if( !pubFile.exists() || !priFile.exists()){
                RasUtils.generateKey( this.pubKeyPath ,this.priKeyPath , this.secret);
            }
            //主要是把路径转为对象
            //3 路径- >对象
            this.publicKey = RasUtils.getPublicKey( this.pubKeyPath );
            this.privateKey = RasUtils.getPrivateKey( this.priKeyPath );
        } catch (Exception e) {
         throw  new RuntimeException(e);
        }
    }
}
