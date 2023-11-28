package com.news.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.entity.Admin;
import com.news.mapper.AdminMapper;
import com.news.service.AdminService;
import org.springframework.stereotype.Service;

/**
* @author ngz
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




