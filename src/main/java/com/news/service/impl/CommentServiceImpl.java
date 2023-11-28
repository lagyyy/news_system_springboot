package com.news.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.entity.Comment;
import com.news.service.CommentService;
import com.news.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author ngz
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




