package com.czxy.changgou3.controller;

import com.czxy.changgou3.pojo.Category;
import com.czxy.changgou3.service.CategoryService;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/2 0002
 **/
@RestController
@RequestMapping("categorys")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping
    public BaseResult findAll(){
        //查询
        List<Category>list=categoryService.findAll();
        //返回
        return  BaseResult.ok("查询成功",list);
    }

}
