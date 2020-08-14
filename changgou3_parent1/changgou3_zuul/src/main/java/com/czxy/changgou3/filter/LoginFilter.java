package com.czxy.changgou3.filter;

import com.czxy.changgou3.config.JwtProperties;
import com.czxy.changgou3.pojo.User;
import com.czxy.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/29 0029
 **/
@Component       //过滤器交于spring
//@EnableConfigurationProperties({类.class,类2.class})
@EnableConfigurationProperties({JwtProperties.class, FilterProties.class})   //如果对应文件没加@componetn需要这行代码
public class LoginFilter extends ZuulFilter {
    //2.2 注入jwt配置类实例
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private FilterProties filterProties;

    @Override
    public String filterType() {      //过滤类型: pre 执行前拦截
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {     //过滤器顺序,此项不要求
        return 5;
    }

    @Override
    public boolean shouldFilter() {  //是否进行过滤,返回值true表示执行run();返回值false表示不执行run()
      //当前请求路径,如果与"白名单"路径匹配,将return false,否则 return true
        //3.1 获得用户请求路径
        // 3.1.1 获得上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 3.1.2 获得request请求对象
        HttpServletRequest request = currentContext.getRequest();
        // 3.1.2 获得请求路径  , /v3/authservice/login
      //URI: 统一资源标识符(部分路径) /v3/cgwebservice/user/checkusername
        //URL:统一资源定位符(完整路径)  http://locahost:10010/v3/cgwebservice/user/checkusername
       String requestURI = request.getRequestURI();


        //3.2 如果路径是 /auth-service/login ，当前拦截不执行
        //获得白名单路径   方式1
        /*        for (String path  : filterProties.getAllowPaths()) {
            //判断包含
            if(requestURI.contains(path)){
                return false;
            }
        }*/
        //获得白名单路径 方式2
        List<String> allowPaths = filterProties.getAllowPaths();
        //3.3 其他都执行 run()方法
        for (String path : allowPaths) {
            if(requestURI.contains(path)){
                return false;
            }

        }
        //其他路径,必须执行run()
        return true;
    }

    @Override
    public Object run() throws ZuulException {  //过滤方法
        //1.获得token
        //1.1.获得上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //1.2 获得request对象
        HttpServletRequest request = currentContext.getRequest();
     //1.3.获得指定请求头的值
        String token = request.getHeader("Authorization");

        //2 校验token -- 使用JWT工具类进行解析
        // 2.1 使用工具类，通过公钥获得对应信息
        try {
            //2.使用工具类解析token(校验token)
            JwtUtils.getObjectFromToken(token,jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            //3.1解析异常,校验没有通过,返回403,并返回提示信息"token无效或失效"
            // 1) 返回提示信息
            currentContext.addOriginResponseHeader("content-type","text/html;charset=UTF-8");
            currentContext.addZuulResponseHeader("content-type","text/html;charset=UTF-8");
          //2)返回403
            currentContext.setResponseStatusCode( 403 );        //响应的状态码：403
            currentContext.setResponseBody("token失效或无效");
            currentContext.setSendZuulResponse( false );        //没有响应内容
        }
        // 2.5 如果没有异常，登录了--放行
        return null;
    }
}
