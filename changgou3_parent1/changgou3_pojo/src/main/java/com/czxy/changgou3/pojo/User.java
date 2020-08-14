package com.czxy.changgou3.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/22 0022
 **/
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    private String email;
    private String mobile;
    private String username;

    private String password;
    private String face;
    private String expriece;
    @Transient  //临时的,不进数据库
 private String code;

/*
CREATE TABLE `tb_user` (
           `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
           `created_at` timestamp NULL DEFAULT NULL,
           `updated_at` timestamp NULL DEFAULT NULL,

           `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Email',
           `mobile` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码',
           `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',

           `password` char(60) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
           `face` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
           `expriece` int(10) unsigned DEFAULT '0' COMMENT '经验值',

           PRIMARY KEY (`id`),
           UNIQUE KEY `users_mobile_unique` (`mobile`),
           UNIQUE KEY `users_name_unique` (`username`),
           UNIQUE KEY `users_email_unique` (`email`)
         ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
 */
}
