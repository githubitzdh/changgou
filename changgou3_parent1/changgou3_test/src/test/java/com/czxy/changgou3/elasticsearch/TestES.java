package com.czxy.changgou3.elasticsearch;

import com.czxy.changgou3.TestApplication;
import com.czxy.changgou3.vo.ESBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/13 0013
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestES {
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void run1() {
        //创建索引
        elasticsearchTemplate.createIndex(ESBook.class);
    }

    @Test
    public void run2() {
        //添加映射
        elasticsearchTemplate.putMapping(ESBook.class);

    }
    @Test
    public void run3() {
        //删除映射
        elasticsearchTemplate.deleteIndex(ESBook.class);
    }
}
