package com.czxy.changgou3.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/2 0002
 **/
@Table(name="tb_category")
@Data
public class Category implements Serializable {
      /*
    CREATE TABLE `tb_category` (
      `id` bigint(20) NOT NULL,
      `cat_name` varchar(20) DEFAULT NULL,
      `parent_id` bigint(20) DEFAULT NULL,
      `is_parent` int(1) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     */
      @Id
      private Integer id;

    @Column(name="cat_name")
    //@JsonProperty("cat_name")
    private String catName;

    @Column(name="parent_id")
    //@JsonProperty("parent_id")
    private Integer parentId;

    @Column(name="is_parent")
    //@JsonProperty("is_parent")
    private Boolean isParent;

    //封装数据:每个分类拥有多个[子分类]
    private List<Category> children = new ArrayList<>();

}
