package com.czxy.changgou3.controller;

import com.czxy.changgou3.pojo.Brand;
import com.czxy.changgou3.service.BrandService;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/7 0007
 **/
@RestController
@RequestMapping("/brands")
public class BrandController {
    @Resource
    private BrandService brandService;

    @GetMapping("/category/{categoryId}")
    public BaseResult findAll(@PathVariable("categoryId") Integer categoryId) {
        //1.查询
        List<Brand> list = brandService.findall(categoryId);
        return BaseResult.ok("查询成功", list);
    }
}
