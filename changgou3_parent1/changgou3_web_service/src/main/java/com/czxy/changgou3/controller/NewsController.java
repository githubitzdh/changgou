package com.czxy.changgou3.controller;

import com.czxy.changgou3.pojo.News;
import com.czxy.changgou3.service.NewsService;
import com.czxy.changgou3.vo.NewsVo;
import com.czxy.vo.BaseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/1 0001
 **/
@RestController
@RequestMapping("/news")
public class NewsController {
@Resource
    private NewsService newsService;

@GetMapping
    public BaseResult findAll(NewsVo newsVo){
    //查询
    PageInfo<News>pageInfo=this.newsService.findAll(newsVo);
    //2.封装
    return BaseResult.ok("成功",pageInfo);

}
}
