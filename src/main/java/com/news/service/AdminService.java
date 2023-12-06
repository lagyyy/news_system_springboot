package com.news.service;

import com.news.domain.ResponseResult;
import com.news.domain.dto.ChangePasswordDto;
import com.news.domain.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.news.domain.query.AdminQuery;

/**
* @author ngz
* @description 针对表【admin】的数据库操作Service
* @createDate 2023-11-28 16:31:38
*/
public interface AdminService extends IService<Admin> {

    ResponseResult login(Admin admin);

    ResponseResult getAdminListPage();

    ResponseResult getAdmin(Long id);
    ResponseResult changePassword(ChangePasswordDto passwordDto);
}
