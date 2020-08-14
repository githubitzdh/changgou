package com.czxy.changgou3.elasticsearch;

import com.czxy.changgou3.TestApplication;
import com.czxy.changgou3.respository.ESBookRespository;
import com.czxy.changgou3.vo.ESBook;
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
 * @date 2020/4/14 0014
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestQurey {
    @Resource
    private ESBookRespository esBookRespository;

    //需求;查询title为"明年
    @Test
    public void run1() {
        //查询条件构建器(存放所有的查询条件)
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //关键字查询match
        queryBuilder.withQuery(QueryBuilders.matchQuery("titles", "明年"));

        //查询
        Page<ESBook> page = esBookRespository.search(queryBuilder.build());

        //处理结果
        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);

        }

    }

    //采用多条件进行查询,查询title为"明年"且,不含"2"
    @Test
    public void run2() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //查询条件--多条件
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        //条件1;必须
        bool.must(QueryBuilders.matchQuery("titles", "明年"));
        //条件2;不需要
        bool.mustNot(QueryBuilders.matchQuery("titles", "2"));

        queryBuilder.withQuery(bool);
        //查询
        Page<ESBook> page = esBookRespository.search(queryBuilder.build());

        //处理结果
        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        //可这样遍历,也可用for循环
        page.forEach(esBook -> {
            System.out.println(esBook);
        });
    }


    //需求:查询"php编程思想"以及"女生"相关数据
    @Test
    public void run3() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.matchQuery("titles", "php编程思想"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("titles", "女生"));


        queryBuilder.withQuery(boolQueryBuilder);

        //查询
        Page<ESBook> page = esBookRespository.search(queryBuilder.build());
        //处理结果
        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);
        }


    }

    //精准查询term
    //#需求：查询价格为12 信息
    @Test
    public void run4() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //查询条件
        queryBuilder.withQuery(QueryBuilders.termQuery("price", 12));

        //查询
        Page<ESBook> page = esBookRespository.search(queryBuilder.build());

        //处理结果
        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);

        }
    }


    //需求:查询结果在10-100之间
    @Test
    public void run5() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //查询条件
        queryBuilder.withQuery(QueryBuilders.rangeQuery("price").gte(10).lte(100));
        //查询
        Page<ESBook> page = esBookRespository.search(queryBuilder.build());

        //处理结果
        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);

        }

    }


    //分页
    @Test
    public void run6() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //查询条件
        int pageNum = 1;  //第几页
        int pageSize = 2;  //每页显示条数
        queryBuilder.withPageable(PageRequest.of(pageNum, pageSize));

        //查询
        Page<ESBook> page = esBookRespository.search(queryBuilder.build());
        //处理结果
        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        System.out.println("第几页:" + page.getNumber());
        System.out.println("每页个数:" + page.getSize());
        for (ESBook esBook : page) {
            System.out.println(esBook);
        }


    }


    //需求;按照价格降序
    @Test
    public void run7() {
        //查询添加构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //查询条件
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));

        //查询
        Page<ESBook> page = esBookRespository.search(queryBuilder.build());

        //处理结果
        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());

        for (ESBook esBook : page) {
            System.out.println(esBook);

        }
    }
}

