package com.czxy.changgou3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/8 0008
 **/
public class test {
    @Test
    public void  run1(){

        String x[]={"a","c","e","c"};
        String y[]={"b","c"};
        String [][]z={
                {"a","c","e","c"},
                {"b","c"}
        };
        HashSet<String> set = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < z.length; i++) {
            for (int j = 0; j < z[i].length; j++) {
                //判断：是否不重复，不重复元素添加到ArrayList
                if(set.add(z[i][j])){
                    //不重复元素添加到ArrayList
                    list.add(z[i][j]);
                } } }
        System.out.println(list);

        for (int i = 0; i < z.length; i++) {


        }
       /* for (String s : x) {
            if(set.add(s)){
                list.add(s);
            }*/

        }
        @Test
    public void run2(){
        int a=10;
            ++a;     //先自增,后赋值,就是11
            System.out.println(++a); //先自增,后赋值,就是12
            System.out.println(a++); //先赋值,就是上一个后赋值的值11+1
        }




    }


