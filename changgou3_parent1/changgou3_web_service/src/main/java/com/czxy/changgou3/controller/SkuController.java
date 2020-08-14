package com.czxy.changgou3.controller;

import com.czxy.changgou3.service.SkuService;
import com.czxy.changgou3.vo.ESData;
import com.czxy.changgou3.vo.OneSkuResult;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/15 0015
 **/
@RestController
@RequestMapping("/sku")
public class SkuController {
    @Resource
    private SkuService skuService;

    @GetMapping("/esData")
    public BaseResult<List<ESData>> findESData() {
        //查询
        List<ESData> esData = skuService.findESData();
        //返回
        return BaseResult.ok("查询成功", esData);
    }

    /**
     * 查询详情
     *
     * @param skuid
     * @return
     */
    @GetMapping("/goods/{skuid}")
    public BaseResult<OneSkuResult> findSkuById(@PathVariable("skuid") Integer skuid) {
        //查询
        OneSkuResult sku = skuService.findSkuByid(skuid);
        //返回
        return BaseResult.ok("查询成功", sku);
    }

    /**
     * 更新库存
     *
     * @param skuid
     * @param count
     * @return
     */
    @PutMapping("/goods/{skuid}")
    public BaseResult updateSkuNum(@PathVariable("skuid") Integer skuid, @RequestParam("count") Integer count) {
        //1.查询
        skuService.updateSkuNum(skuid, count);
        //2.返回
        return BaseResult.ok("更新成功");
    }
}
