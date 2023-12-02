package com.news.service;

import com.news.domain.ResponseResult;
import com.news.domain.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.news.domain.query.NewsQuery;

/**
* @author ngz
* @description 针对表【news】的数据库操作Service
* @createDate 2023-11-28 16:31:38
*/
public interface NewsService extends IService<News> {

    ResponseResult getNewsListPage(Long current, Long limit, NewsQuery newsQuery);

    ResponseResult getNewsOne(Long id);

    ResponseResult getAdminNewsListPage(Long current, Long limit, NewsQuery newsQuery);
}
