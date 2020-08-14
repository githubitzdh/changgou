package com.czxy.changgou3.respository;

import com.czxy.changgou3.vo.ESBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/13 0013
 **/
public interface ESBookRespository extends ElasticsearchRepository<ESBook, Long> {
    //实例1.根据title进行查询
    public List<ESBook> findByTitles(String title);

    //实例2;区间查询,价格1-200
    public List<ESBook> findByPriceBetween(Float start, Float end);

    //实例3.查询价格大于>=123
    public List<ESBook> findByPriceGreaterThanEqual(Float price);

    //实例4;区间查询价格400-600,且按照images降序排序
    public List<ESBook> findByPriceBetweenOrderByImagesDesc(Float a, Float b);


}
