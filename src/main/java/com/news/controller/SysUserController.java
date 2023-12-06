package com.news.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.news.domain.ResponseResult;
import com.news.domain.dto.RegisterUserDto;
import com.news.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @PostMapping("register")
    public ResponseResult registerUser(@RequestBody RegisterUserDto registerUserDto){
        return sysUserService.registerUser(registerUserDto);
    }

    @PostMapping("login")
    public ResponseResult login(@RequestBody RegisterUserDto registerUserDto){
        return sysUserService.login(registerUserDto);
    }
    @PostMapping("isLogin")
    public ResponseResult isLogin(){
        boolean login = StpUtil.isLogin();
        if (!login){
            return ResponseResult.okResult(202,"未登录");
        }else return ResponseResult.okResult(200,"已登录");

    }

}
