package com.czxy.changgou3.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/1 0001
 **/
@Table(name = "tb_news")
@Data
@NoArgsConstructor
public class News implements Serializable {
  /*
    CREATE TABLE `tb_news` (
           `id` int(11) NOT NULL,
           `title` varchar(20) DEFAULT NULL,
           `content` text,
           `author` varchar(255) DEFAULT NULL,
           `created_at` date DEFAULT NULL,
           `updated_at` date DEFAULT NULL,
           PRIMARY KEY (`id`)
         ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
     */
  @Id
    private Integer id;

  @Column(name = "title")
    private String title;

  @Column(name = "content")
    private String content;

  @Column(name = "author")
    private String author;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;
}
