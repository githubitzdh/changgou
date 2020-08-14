package com.czxy.changgou3.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/7 0007
 **/
@Data
//@Entity
@Table(name="tb_specification")
public class Specification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="spec_name")
    //@JsonProperty("spec_name")
    private String specName;

    @Column(name="category_id")
    private Integer categoryId;                     //分类外键
    private Category category;                      //分类外键对应对象


    private List<SpecificationOption> options=new ArrayList<>();      //一个规格，具有多个规格选项

}
