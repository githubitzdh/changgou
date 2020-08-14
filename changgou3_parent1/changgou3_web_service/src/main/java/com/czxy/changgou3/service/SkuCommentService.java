package com.czxy.changgou3.service;

import com.czxy.changgou3.mapper.ImpressionMapper;
import com.czxy.changgou3.mapper.SkuCommentMappper;
import com.czxy.changgou3.pojo.Impression;
import com.czxy.changgou3.pojo.SkuComment;
import com.czxy.changgou3.vo.CommentResult;
import com.czxy.changgou3.vo.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/23 0023
 **/
@Service
@Transactional
public class SkuCommentService {
    @Resource
    private SkuCommentMappper skuCommentMappper;
    @Resource
    private ImpressionMapper impressionMapper;

    /**
     * 查询评论
     *
     * @param spuid
     * @param pageRequest
     * @return
     */
    public CommentResult findComments(Integer spuid, PageRequest pageRequest) {
        //1.创建封装对象
        CommentResult commentResult = new CommentResult();
        //2.封装数据
        //2.1 买家印象
        List<Impression> impressionList = impressionMapper.findImpressionsBySpuid(spuid);
        commentResult.setImpressions(impressionList);

        //2.2 好评度
        // 查询好评
        Integer goodcount = skuCommentMappper.findCommentCountByRatio(spuid, 0);
        //查询中评
        Integer commentCount = skuCommentMappper.findCommentCountByRatio(spuid, 1);
        //查询差评
        Integer badCount = skuCommentMappper.findCommentCountByRatio(spuid, 2);
        //总评论数
        Integer totalCount = skuCommentMappper.findNumBySpuId(spuid);

        HashMap<String, String> map = new HashMap<>();
        if (totalCount != 0) {
            map.put("goods", String.format("%.2f", goodcount * 100f / totalCount));
            map.put("common", String.format("%.2f", commentCount * 100f / totalCount));
            map.put("bad", String.format("%.2f", badCount * 100f / totalCount));
        } else {
            map.put("goods","0");
            map.put("common", "0");
            map.put("bad","0");
        }
        commentResult.setRatio(map);

        //3.进行分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());

        //3.1分页查询
        List<SkuComment> comments = skuCommentMappper.findCommentsBySpuid(spuid);
        PageInfo pageInfo = new PageInfo(comments);

        //3.2 评论总数
        commentResult.setCommentCount(pageInfo.getTotal());

        //3.3 评论列表
        commentResult.setComments(pageInfo.getList());

        //4.返回
        return commentResult;
    }
}

