package com.czxy.changgou3.controller;

import com.czxy.changgou3.service.SkuSearchService;
import com.czxy.changgou3.vo.SearchVo;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/16 0016
 **/
@RestController
@RequestMapping("/sku")
public class SkuSearchController {
@Resource
    private SkuSearchService skuSearchService;

@PostMapping("/search")
    public BaseResult findSkus(@RequestBody SearchVo searchVo){
    if(searchVo.getCatId()==null){
        return BaseResult.error("分类不能为空");
    }
    //查询
    Map search= skuSearchService.search(searchVo);
   //返回
    return BaseResult.ok("查询成功",search);
}
}
