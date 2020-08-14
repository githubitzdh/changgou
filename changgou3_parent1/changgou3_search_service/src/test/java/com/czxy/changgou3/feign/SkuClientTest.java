package com.czxy.changgou3.feign;

import com.czxy.changgou3.SearchServiceApplication;
import com.czxy.changgou3.repository.SkuRepository;
import com.czxy.changgou3.vo.SearchSku;
import com.czxy.vo.BaseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/16 0016
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchServiceApplication.class)
public class SkuClientTest {
    @Resource
    private SkuFeign skuFeign;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
   @Resource
   private SkuRepository skuRepository;
    @Test
    public void run1(){
        BaseResult<List<SearchSku>> elist = skuFeign.findESData();
        List<SearchSku> list = elist.getData();
        System.out.println(list);
    }

    @Test
    public void run2(){
        //删除所有
        elasticsearchTemplate.deleteIndex(SearchSku.class);
        //构建索引
        elasticsearchTemplate.createIndex(SearchSku.class);
        elasticsearchTemplate.putMapping(SearchSku.class);

    }

    @Test
    public void run3(){
      //使用feign查询数据,将查询结果添加es
        //1.使用feign查询
        BaseResult<List<SearchSku>> baseResult = skuFeign.findESData();
        List<SearchSku> list = baseResult.getData();
        //2将查询结果添加到es中
        skuRepository.saveAll(list);
    }


}
