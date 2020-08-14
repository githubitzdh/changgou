package com.czxy.changgou3.service;

import com.czxy.changgou3.mapper.NewsMapper;
import com.czxy.changgou3.pojo.News;
import com.czxy.changgou3.vo.NewsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/1 0001
 **/
@Service
@Transactional
public class NewsService {
    @Resource
    private NewsMapper newsMapper;


    public PageInfo<News> findAll(NewsVo newsVo) {
        //1.分页
        PageHelper.startPage(newsVo.getPageNum(), newsVo.getPageSize());


        //2.排序(默认使用创建时间进行排序)
        Example example = new Example(News.class);
        if ("desc".equals(newsVo.getSortWay())) {
            example.setOrderByClause("created_at desc");
        } else {
            example.setOrderByClause("created_at asc");
        }
        //3.查询所有
        List<News> list = newsMapper.selectByExample(example);
        return new PageInfo<>(list);

    }
}
