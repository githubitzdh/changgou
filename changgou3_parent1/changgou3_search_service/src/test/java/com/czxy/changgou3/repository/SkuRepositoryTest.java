package com.czxy.changgou3.repository;

import com.czxy.changgou3.SearchServiceApplication;
import com.czxy.changgou3.vo.SearchSku;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/16 0016
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchServiceApplication.class)
public class SkuRepositoryTest {
    @Resource
    private SkuRepository skuRepository;

    @Test
    public void run1(){
         //1.多条件拼凑
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //1.1查询catId=76的数据
        //boolQueryBuilder.must(QueryBuilders.termQuery("catId",76));

        //1.2品牌"brandId" :15127
        //boolQueryBuilder.must(QueryBuilders.termQuery("brandId",15127));

        //1.3 "specs" :{"机身颜色" ,"粉色" }   一条
        //boolQueryBuilder.must(QueryBuilders.termQuery("specs.机身颜色.keyword","粉色"));


        //1.4.区间查询;价格 110000-140000
   //boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(110000).lte(140000));




        //2.查询条件构建器:整合多条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      //2.1整合多条件
       queryBuilder.withQuery(boolQueryBuilder);
  //2.2 整合分页(page从0开始第一页)
        queryBuilder.withPageable(PageRequest.of(0,2));

        //2.3整合排序
     queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        //3.查询
        Page<SearchSku> page = skuRepository.search(queryBuilder.build());


        //4处理结果
        System.out.println("总条数:"+page.getTotalElements());
        for (SearchSku searchSku : page.getContent()) {
            System.out.println(searchSku);

        }

    }
}
