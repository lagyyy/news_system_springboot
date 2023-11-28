package com.news.domain;

import lombok.Data;

/**
 * @program: ZLSchool->FileUploadResult
 * @author: 工藤新一
 * @create: 2022-11-02 19:49
 **/
@Data
public class FileUploadResult {
    // 文件名
    private int id;
    private String url;
    // 状态有：uploading done error removed

}
