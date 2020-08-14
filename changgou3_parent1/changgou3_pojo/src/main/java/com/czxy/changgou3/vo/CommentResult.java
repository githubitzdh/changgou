package com.czxy.changgou3.vo;

import com.czxy.changgou3.pojo.Impression;
import com.czxy.changgou3.pojo.SkuComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/23 0023
 **/
@Data
public class CommentResult {
    private List<Impression> impressions;       //买家印象
    private Map<String, String> ratio;           //好评度
    @JsonProperty("comment_count")
    private Long commentCount;             //评论数
    private List<SkuComment> comments;          //评论列表
}
