package com.czxy.changgou3.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/7 0007
 **/
//@Entity
@Table(name="tb_specification_option")
@Data
public class SpecificationOption implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         //自动增长的
    private Integer id;

    @Column(name="spec_id")
    //@JsonProperty("spec_id")
    private Integer specId;                //外键
    private Specification specification; //外键对应对象

    @Column(name="option_name")
    //@JsonProperty("option_name")
    private String optionName;
}
