package com.news.controller;

import com.news.domain.ResponseResult;
import com.news.domain.query.NewsQuery;
import com.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @PostMapping("newsPageCondition/{current}/{limit}")
    public ResponseResult getNewsListPage( @PathVariable Long current,
                                           @PathVariable Long limit,
                                           @RequestBody(required = false)NewsQuery newsQuery){
        return newsService.getNewsListPage(current, limit, newsQuery);
    }

    @GetMapping("getOne/{id}")
    public ResponseResult getNewsOne(@PathVariable Long id){
        return newsService.getNewsOne(id);
    }
}
