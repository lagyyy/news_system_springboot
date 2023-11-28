package com.news.util;


import cn.hutool.core.lang.UUID;

public class FileUtils {
    // 文件重命名 保留扩展名
    public static String rename(String filename) {
        String extname = filename.substring(filename.lastIndexOf("."));
        String newName = UUID.randomUUID().toString() + extname;
        return newName;
    }
}

