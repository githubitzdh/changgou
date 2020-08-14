package com.czxy.changgou3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/13 0013
 **/
@Document(indexName = "czxy1", type = "book", shards = 4, replicas = 2)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ESBook {
    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String titles;

    @Field(type = FieldType.Keyword, index = true)
    private String images;

    @Field(type = FieldType.Float)
    private Float price;


}
