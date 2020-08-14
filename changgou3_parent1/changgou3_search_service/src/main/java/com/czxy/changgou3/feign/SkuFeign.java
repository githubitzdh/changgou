package com.czxy.changgou3.feign;

import com.czxy.changgou3.vo.SearchSku;
import com.czxy.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/16 0016
 **/
@FeignClient(value = "cgwebservice",path = "/sku")
public interface SkuFeign {
    /**
     * 查询所有
     * @return
     */
    @GetMapping("/esData")
    public BaseResult<List<SearchSku>> findESData();
}
