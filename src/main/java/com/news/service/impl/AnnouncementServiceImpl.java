package com.news.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.entity.Announcement;
import com.news.mapper.AnnouncementMapper;
import com.news.service.AnnouncementService;
import org.springframework.stereotype.Service;

/**
* @author ngz
* @description 针对表【announcement】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
    implements AnnouncementService{

}




