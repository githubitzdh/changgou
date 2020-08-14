package com.czxy.changgou3.elasticsearch;

import com.czxy.changgou3.TestApplication;
import com.czxy.changgou3.respository.ESBookRespository;
import com.czxy.changgou3.vo.ESBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/13 0013
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestESFind {
    @Resource
    private ESBookRespository esBookRespository;

    @Test
    public void run1() {
        //查询所有
        Iterable<ESBook> all = esBookRespository.findAll();
        Iterator<ESBook> iterator = all.iterator();
        while (iterator.hasNext()) {
            ESBook esbook = iterator.next();
            System.out.println(esbook);
        }

    }

    @Test
    public void run2() {
        //通过id查询详情
        Optional<ESBook> op = esBookRespository.findById(2L);
        ESBook esBook = op.get();
        System.out.println(esBook);


    }

    @Test
    public void run3() {
        List<ESBook> list = esBookRespository.findByTitles("年");
        System.out.println(list);
    }


    @Test
    public void run4() {
        List<ESBook> b = esBookRespository.findByPriceBetween(1f, 200f);
        System.out.println(b);

    }

    @Test
    public void run5() {
        List<ESBook> byPriceGreaterThanEqual = esBookRespository.findByPriceGreaterThanEqual(123f);
        System.out.println(byPriceGreaterThanEqual);

    }

    @Test
    public void run6() {
        List<ESBook> list = esBookRespository.findByPriceBetweenOrderByImagesDesc(400f, 600f);
        System.out.println(list);
    }

}
