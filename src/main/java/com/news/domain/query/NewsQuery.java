package com.news.domain.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewsQuery {
    @ApiModelProperty(value = "栏目")
    /**
     * 关联栏目
     */
    private Integer categoryId;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
