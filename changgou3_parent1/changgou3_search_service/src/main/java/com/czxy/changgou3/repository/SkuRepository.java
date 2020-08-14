package com.czxy.changgou3.repository;

import com.czxy.changgou3.vo.SearchSku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/16 0016
 **/
public interface SkuRepository extends ElasticsearchRepository<SearchSku,Long> {


}
