package com.czxy.changgou3.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/23 0023
 **/
@Table(name = "tb_impression")
@Data
public class Impression implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer count;
    @Column(name = "spu_id")
    //@JsonProperty("spu_id")
    private Integer spuId;
    @Column(name = "sku_id")
    //@JsonProperty("sku_id")
    private Integer skuId;
}
