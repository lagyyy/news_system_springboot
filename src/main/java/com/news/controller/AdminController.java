package com.news.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.news.domain.ResponseResult;
import com.news.domain.dto.RegisterUserDto;
import com.news.domain.entity.Admin;
import com.news.domain.query.AdminQuery;
import com.news.domain.query.NewsQuery;
import com.news.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("login")
    public ResponseResult login(@RequestBody Admin admin){
        return adminService.login(admin);
    }

    @PostMapping("add")
    public ResponseResult addAdmin(@RequestBody Admin admin){
        boolean save = adminService.save(admin);
        if (save){
            return ResponseResult.okResult();
        }else return ResponseResult.okResult(202);
    }
    @PostMapping("list")
    public ResponseResult getAdminListPage( ){
        return adminService.getAdminListPage();
    }

    @PostMapping("isLogin")
    public ResponseResult isLogin(){
        boolean login = StpUtil.isLogin();
        if (!login){
            return ResponseResult.okResult(202,"未登录");
        }else return ResponseResult.okResult(200,"已登录");

    }
}
