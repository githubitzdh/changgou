package com.czxy.changgou3.elasticsearch;

import com.czxy.changgou3.TestApplication;
import com.czxy.changgou3.respository.ESBookRespository;
import com.czxy.changgou3.vo.ESBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/13 0013
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestESData {
    @Resource
    private ESBookRespository esBookRespository;

    @Test
    public void run1(){
        //添加一个数据
        //准备数据
        ESBook esBook = new ESBook(55L,"煌sir的风流史","10.jpg",222f);
        //添加
        esBookRespository.save(esBook);

    }

    @Test
    public void run2(){
        //添加一组数据
        //准备数据
        ArrayList<ESBook> list = new ArrayList<>();
      list.add(new ESBook(59L,"php编程思想","6.jpg",12f));
      list.add(new ESBook(99L,"女生","7.jpg",56f));
      list.add(new ESBook(70L,"java编程思想","8.jpg",89f));

        //添加
        esBookRespository.saveAll(list);
    }

    @Test
    public void run3(){
        //修改数据
        //准备数据
        ESBook esBook = new ESBook(1L,"那年年","1.jpg",998f);
        //添加
        esBookRespository.save(esBook);


    }
    @Test
    public void run4(){
        //删除数据
        //准备数据
        ESBook esBook = new ESBook();
        esBook.setId(1L);
        //添加
        esBookRespository.delete(esBook);

    }
}
