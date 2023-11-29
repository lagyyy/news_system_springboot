package com.news.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class CommentListVo  {
    private List<NewsCommentVo> commentList;
    private int readNumber;
}
