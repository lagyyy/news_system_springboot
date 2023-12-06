package com.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.ResponseResult;
import com.news.domain.entity.Announcement;
import com.news.domain.entity.Category;
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

    @Override
    public ResponseResult getFirst() {
        QueryWrapper<Announcement> announcementQueryWrapper = new QueryWrapper<>();
        announcementQueryWrapper.orderByDesc("publish_date");
        Announcement one = getOne(announcementQueryWrapper,false);
        return ResponseResult.okResult(one);
    }
}




