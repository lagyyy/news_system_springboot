package com.news.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.entity.SysUser;
import com.news.service.SysUserService;
import com.news.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author ngz
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




