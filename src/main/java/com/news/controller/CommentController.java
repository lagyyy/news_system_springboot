package com.news.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.news.domain.ResponseResult;
import com.news.domain.dto.CommentDto;
import com.news.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("insertOne")
    @ApiOperation(value = "评论接口",notes = "插入一条评论")
    public ResponseResult insertCommentOnt(@RequestBody CommentDto comment){
        System.out.println(comment);
        return commentService.insertOneComment(comment);
    }

    @GetMapping("oneComments/{newsId}/{userId}")
    public ResponseResult queryOneArticleComment(@PathVariable int newsId,@PathVariable int userId){

//        Object loginId = StpUtil.getLoginId();
//        System.out.println(loginId);
        return commentService.queryOneArticleComment(newsId, userId);
    }
    @GetMapping("deleteComments/{newsId}/{userId}")
    public ResponseResult deleteComment(@PathVariable int newsId,@PathVariable int userId){

//        Object loginId = StpUtil.getLoginId();
//        System.out.println(loginId);
        return commentService.queryOneArticleComment(newsId, userId);
    }
}
