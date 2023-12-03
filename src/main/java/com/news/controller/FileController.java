package com.news.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.img.Img;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.news.domain.FileUploadResult;
import com.news.domain.ResponseResult;
import com.news.domain.entity.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Value("${spring.servlet.multipart.location}")
    private String fileRootPath;
    @Value("${file.upload.relativePath}")
    private String relativePath;
    @PostMapping("/upload")
    public FileUploadResult upload(@RequestParam("file") MultipartFile file)
    {
        long size = file.getSize();
        System.out.println(size);
        try {
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            //获取后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //文件保存路径
            String filePath = fileRootPath;
            //文件重命名,防止重复
            fileName = UUID.randomUUID() + fileName;
            String saveFileName = filePath + fileName;
            System.out.println(saveFileName);
            //文件对象
            File dest = new File(saveFileName);

            //判断路径是否存在，如果不存在则创建
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            //保存到服务器
            try {
                file.transferTo(dest);
                String type = FileTypeUtil.getType(dest);
                System.out.println(type);
                if (("gif").equals(type) || ("GIF").equals(type) ||
                        ("jpg").equals(type) || ("JPG").equals(type) ||
                        ("PNG").equals(type) || ("png").equals(type)) {
                    //这里是正确的图片格式
                    FileUploadResult fileUploadResult = new FileUploadResult();
                    UploadFile uploadFile = new UploadFile();
                    uploadFile.setUrl("http://127.0.0.1:8089"+relativePath + fileName);
                    fileUploadResult.setData(uploadFile);
                    System.out.println(fileUploadResult);
                    return fileUploadResult;
//                Img.from(dest);
//                        .setQuality(0.5)//压缩比率
//                        .write(dest);
//                System.out.println("图片没问题");
                } else if ("mp4".equals(type)) {
                    System.out.println("上传了视频");
                    FileUploadResult fileUploadResult = new FileUploadResult();
                    UploadFile uploadFile = new UploadFile();
                    uploadFile.setUrl("http://127.0.0.1:8089"+relativePath + fileName);
                    System.out.println(fileUploadResult);
                    return fileUploadResult;
                } else {
                    boolean del = FileUtil.del(dest);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {

        }

        return null;
    }
}
