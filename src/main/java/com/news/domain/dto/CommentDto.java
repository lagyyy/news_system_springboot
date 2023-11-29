package com.news.domain.dto;


import lombok.Data;

/**
 * @program: ZLSchool->CommentDto
 * @author: 工藤新一
 * @create: 2022-11-17 14:06
 **/
@Data
public class CommentDto {


    /**
     * 文章id
     */
    private int newsId;

    /**
     * 根评论id
     */
    private int userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;

    /**
     * 回复目标评论id
     */
    private int parentId;


}
