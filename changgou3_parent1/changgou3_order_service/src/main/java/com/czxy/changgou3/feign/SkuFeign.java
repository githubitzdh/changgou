package com.czxy.changgou3.feign;

import com.czxy.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhandehuang@itcast.cn
 * @date 2020/5/4 0004
 **/
@FeignClient(value = "cgwebservice",path = "/sku")
public interface SkuFeign {
    /**
     * 修改库存
     * @param skuid
     * @param count
     * @return
     */
    @PutMapping("/goods/{skuid}")
    public BaseResult updateSkuNum(@PathVariable("skuid")Integer skuid, @RequestParam("count")Integer count);


}
