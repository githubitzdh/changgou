package com.czxy.changgou3.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/29 0029
 **/
@Data   //getter和setter
@ConfigurationProperties(prefix="sc.filter")
@Component  //交于spring容器
public class FilterProties {
    //允许访问路径集合
    private List<String> allowPaths;
}
