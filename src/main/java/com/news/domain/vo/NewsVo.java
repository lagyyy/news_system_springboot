package com.news.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NewsVo {
    private Integer id;

    /**
     * 新闻标题
     */
    private String title;

    private String nickName;

    private String avatar;
    private String description;
    private String picture;

    /**
     * 新闻内容
     */
    private String content;

    /**
     * 关联栏目
     */
    private Integer categoryId;

    /**
     * 发布日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date publishDate;

    /**
     * 新闻是否删除
     */
    private String flagDelete;

    /**
     * 标识发布者
     */
    private Long adminId;
}
