package com.news.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.ResponseResult;
import com.news.domain.dto.ChangePasswordDto;
import com.news.domain.entity.Admin;
import com.news.domain.vo.AdminVo;
import com.news.mapper.AdminMapper;
import com.news.service.AdminService;
import com.news.util.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author ngz
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{
    String key = "jiemideshitouzhub";
    @Override
    public ResponseResult login(Admin admin) {
        String password = SaSecureUtil.aesEncrypt(key, admin.getPassword());
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(Admin::getUsername, admin.getUsername())
                .eq(Admin::getPassword, password);

        Admin one = getOne(lambdaQueryWrapper);
        if (ObjectUtil.isNotNull(one)){
            StpUtil.login("admin"+one.getId());
            //获取Token相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            AdminVo adminVo = BeanCopyUtils.copyBean(one, AdminVo.class);
            Map<String,Object> userVoMap = new HashMap<>();
            userVoMap.put(tokenInfo.getTokenName(),tokenInfo.getTokenValue());
            userVoMap.put("user",adminVo);
            return ResponseResult.okResult(userVoMap);
        }
        return ResponseResult.okResult(203,"用户或者密码错误");
    }

    @Override
    public ResponseResult getAdminListPage() {
        List<Admin> list = list();
        List<AdminVo> adminVos = BeanCopyUtils.copyBeanList(list, AdminVo.class);
        return ResponseResult.okResult(adminVos);
    }
    @Override
    public ResponseResult changePassword(ChangePasswordDto passwordDto) {
        Object loginId = StpUtil.getLoginId();
        Admin admin = getById(Long.parseLong(loginId.toString()));
        String old = SaSecureUtil.aesDecrypt(key, admin.getPassword());
        if (!old.equals(passwordDto.getOldPassword())){
            return ResponseResult.okResult(202,"旧密码输入错误");
        }
        LambdaUpdateWrapper<Admin> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        String newPassword = SaSecureUtil.aesEncrypt(key, passwordDto.getPassword());
        lambdaUpdateWrapper.set(Admin::getPassword,newPassword);
        boolean update = update(null, lambdaUpdateWrapper);
        if (update==true){
            return ResponseResult.okResult("更改成功");
        }else {
            return ResponseResult.okResult("网络错误");
        }
    }
    @Override
    public ResponseResult getAdmin(Long id) {
        Admin byId = getById(id);
        AdminVo adminVo = BeanCopyUtils.copyBean(byId, AdminVo.class);
        return ResponseResult.okResult(adminVo);
    }
}




