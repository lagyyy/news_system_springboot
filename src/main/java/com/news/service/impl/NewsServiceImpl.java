package com.news.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.entity.News;
import com.news.service.NewsService;
import com.news.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
* @author ngz
* @description 针对表【news】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService {

}




