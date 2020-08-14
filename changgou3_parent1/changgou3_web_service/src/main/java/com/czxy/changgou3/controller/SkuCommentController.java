package com.czxy.changgou3.controller;

import com.czxy.changgou3.service.SkuCommentService;
import com.czxy.changgou3.vo.CommentResult;
import com.czxy.changgou3.vo.PageRequest;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/23 0023
 **/
@RestController
@RequestMapping("/comments")
public class SkuCommentController {
    @Resource
    private SkuCommentService skuCommentService;

    /**
     * 查询评论
     *
     * @param spuid
     * @param pageRequest
     * @return
     */
    @GetMapping("/spu/{spuid}")
    public BaseResult findCommentsByPage(@PathVariable("spuid") Integer spuid, PageRequest pageRequest) {
        //查询
        CommentResult commentResult = skuCommentService.findComments(spuid, pageRequest);
//返回
        return BaseResult.ok("查询成功", commentResult);
    }
}
