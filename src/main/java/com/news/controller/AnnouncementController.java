package com.news.controller;

import cn.hutool.core.date.DateTime;
import com.news.domain.ResponseResult;
import com.news.domain.entity.Announcement;
import com.news.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("add")
    public ResponseResult insert(@RequestBody Announcement announcement){
        announcement.setPublishDate(DateTime.now());
        boolean save = announcementService.save(announcement);
        if (save){
            return ResponseResult.okResult();
        }return ResponseResult.errorResult(202,"网络错误");
    }
    @GetMapping("list")
    public ResponseResult getList(){
        return ResponseResult.okResult(announcementService.list());
    }
    @GetMapping("/delete")
    public ResponseResult delete(int id){
        boolean b = announcementService.removeById(id);
        if (b){
            return ResponseResult.okResult();
        }return ResponseResult.errorResult(202,"网络错误");
    }


    @GetMapping("/first")
    public ResponseResult getFirst(){
        return announcementService.getFirst();
    }
}
