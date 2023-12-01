package com.news.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.ResponseResult;
import com.news.domain.dto.RegisterUserDto;
import com.news.domain.entity.SysUser;
import com.news.domain.vo.UserVo;
import com.news.service.SysUserService;
import com.news.mapper.SysUserMapper;
import com.news.util.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author ngz
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{
    String key = "jiemideshitouzhub";
    @Override
    public ResponseResult registerUser(RegisterUserDto registerUserDto) {
//        if (!Validator.isMobile(registerUserDto.getPhoneNumber())){
//            return ResponseResult.okResult(202,"请输入正确的手机号");
//        }
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(SysUser::getPhoneNumber,registerUserDto.getPhoneNumber());
        SysUser one = getOne(sysUserLambdaQueryWrapper,false);
        if (ObjectUtil.isNotNull(one)){
            return ResponseResult.okResult(205,"注册失败，该用户已经存在");
        }

        System.out.println(registerUserDto);
        SysUser sysUser = BeanCopyUtils.copyBean(registerUserDto, SysUser.class);
        // 定义秘钥和明文
        String key = "jiemideshitouzhub";
        String text = sysUser.getPassword();
        String ciphertext = SaSecureUtil.aesEncrypt(key, text);
        System.out.println("AES加密后：" + ciphertext);
        sysUser.setPassword(ciphertext);
        sysUser.setCreateTime(DateTime.now());
        sysUser.setAvatar("https://ngz.oss-cn-hangzhou.aliyuncs.com/bbs_img/avatar.jpg");
        sysUser.setNickName("未命名");
        boolean save = save(sysUser);
        if (save==true){
            return ResponseResult.okResult(200,"注册成功");
        }
        return ResponseResult.okResult(202,"注册失败");
    }

    @Override
    public ResponseResult login(RegisterUserDto registerUserDto) {
//        if (!Validator.isMobile(registerUserDto.getPhoneNumber())){
//            return ResponseResult.okResult(202,"请输入正确的手机号");
//        }
        String password = SaSecureUtil.aesEncrypt(key, registerUserDto.getPassword());
        SysUser sysUser = BeanCopyUtils.copyBean(registerUserDto, SysUser.class);
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(SysUser::getPhoneNumber, sysUser.getPhoneNumber())
                .eq(SysUser::getPassword, password);

        SysUser one = getOne(lambdaQueryWrapper);
        if (one != null){
            StpUtil.login(one.getUserId());
            //获取Token相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            UserVo userVo = BeanCopyUtils.copyBean(one, UserVo.class);
            Map<String,Object> userVoMap = new HashMap<>();
            userVoMap.put(tokenInfo.getTokenName(),tokenInfo.getTokenValue());
            userVoMap.put("user",userVo);
            return ResponseResult.okResult(userVoMap);
        }
        return ResponseResult.okResult(203,"用户或者密码错误");
    }
}




