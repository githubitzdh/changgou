package com.czxy.changgou3.controller;

import com.czxy.changgou3.pojo.Specification;
import com.czxy.changgou3.service.SpecificationService;
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
@RequestMapping("/specifications")
public class SpecificationController {
@Resource
    private SpecificationService specificationService;
@GetMapping("/category/{categoryId}")
    public BaseResult findSpecificationByCategoryId(@PathVariable("categoryId")Integer categoryId){
    List<Specification>list=specificationService.specificationService(categoryId);
return BaseResult.ok("查询成功",list);
}

}
