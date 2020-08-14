package com.czxy.changgou3.config;

import com.czxy.utils.RasUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
配置类,用于存放application.yml /sc.jwt中的配置信息
 **/
@Data       //getter 和setter
@ConfigurationProperties(prefix = "sc.jwt")  //加载yml文件中的指定内容
@Component    //让当前类加载到spring容易中
public class JWTProperties {
    private  String secret;  //密钥
    private String pubKeyPath;  //公钥
    private  String priKeyPath;  //私钥
    private int expire;// token过期时间

    //工具类使用中,需要不是路径, 而是对象
    //公钥/私钥的路径  -->公钥/私钥的对象
    private PublicKey publicKey; // 公钥

    private PrivateKey privateKey; // 私钥



    //private static final Logger logger = LoggerFactory.getLogger(JWTProperties.class);

    //spring  声明周期,初始化
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
            //3 路径- >对象
            this.publicKey = RasUtils.getPublicKey( this.pubKeyPath );
            this.privateKey = RasUtils.getPrivateKey( this.priKeyPath );
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }

}
