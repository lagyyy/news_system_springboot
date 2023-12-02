package com.news.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AdminVo {
    private Long id;

    private String nickName;

    private String avatar;

    /**
     * 管理员用户名
     */
    private String username;


}
