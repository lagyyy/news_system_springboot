package com.news.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class NewsCommentVo {
    //    唯一主键
    private int id;
    //    是否是拥有者
    private boolean owner;
    //    点赞数量
    private int likeNum;
    //    评论用户Id
    private Long userId;
    //    点赞者的头像
    private String avatarUrl;
    //    评论者的昵称
    private String nickName;
    //    是否点赞
    private boolean hasLike;
    //    评论内容
    private String content;
    //    所属评论的唯一主键，一级评论为Null
    private int parentId;
    //    评论时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date createTime;
}
