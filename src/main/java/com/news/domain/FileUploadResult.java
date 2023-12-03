package com.news.domain;

import com.news.domain.entity.UploadFile;
import lombok.Data;

/**
 * @program: ZLSchool->FileUploadResult
 * @author: 工藤新一
 * @create: 2022-11-02 19:49
 **/
@Data
public class FileUploadResult {
    // 文件名
    private int errno = 0;
    private UploadFile data;
    // 状态有：uploading done error removed

}
