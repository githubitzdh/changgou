package com.czxy.changgou3.feign;

import com.czxy.changgou3.vo.OneSkuResult;
import com.czxy.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/27 0027
 **/
@FeignClient(value = "cgwebservice",path = "/sku")
public interface SKUFeign {
    /**
     * 查询详情
     *
     * @param skuid
     * @return
     */
    @GetMapping("/goods/{skuid}")
    public BaseResult<OneSkuResult> findSkuById(@PathVariable("skuid") Integer skuid);
}
