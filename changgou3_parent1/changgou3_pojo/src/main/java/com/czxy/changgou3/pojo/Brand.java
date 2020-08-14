package com.czxy.changgou3.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/7 0007
 **/
@Data
@Table(name = "tb_brand")
public class Brand implements Serializable {
    @Id
    private Integer id;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "logo")
    private String logo;

}
