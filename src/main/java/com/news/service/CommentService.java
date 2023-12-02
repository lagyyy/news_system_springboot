package com.news.service;

import com.news.domain.ResponseResult;
import com.news.domain.dto.CommentDto;
import com.news.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ngz
* @description 针对表【comment(评论表)】的数据库操作Service
* @createDate 2023-11-28 16:31:38
*/
public interface CommentService extends IService<Comment> {

    ResponseResult insertOneComment(CommentDto comment);

    ResponseResult queryOneNewsComment(int articleId,int userId);

    ResponseResult deleteComment(int commentId, int userId);
}
