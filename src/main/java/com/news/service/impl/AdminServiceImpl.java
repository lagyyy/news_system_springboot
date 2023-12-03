package com.news.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.ResponseResult;
import com.news.domain.entity.Admin;
import com.news.domain.entity.SysUser;
import com.news.domain.query.AdminQuery;
import com.news.domain.vo.AdminVo;
import com.news.domain.vo.UserVo;
import com.news.mapper.AdminMapper;
import com.news.service.AdminService;
import com.news.util.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
//        Page<Repair> repairPage = new Page<>(current, limit);
//        QueryWrapper<Repair> wrapper = new QueryWrapper<>();
//        if (repairQuery != null) {
//            String address = repairQuery.getAddress();
//            String begin = repairQuery.getBegin();
//            String end = repairQuery.getEnd();
//            String status = repairQuery.getStatus();
//
//            if (!StringUtils.isEmpty(status)) {
//                wrapper.eq("status", status);
//            }
//            if (!StringUtils.isEmpty(address)) {
//                wrapper.like("address", address);
//            }
//            if (!StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)) {
//                wrapper.between("create_time", begin, end);
//            }
//            wrapper.orderByDesc("create_time");
//        }
//        page(repairPage, wrapper);
//        long total = repairPage.getTotal();
//        List<Repair> records = repairPage.getRecords();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("total", total);
//        List<RepairVo> repairUserVos1 = BeanCopyUtils.copyBeanList(records, RepairVo.class);
//        for (RepairVo r : repairUserVos1){
//            Long userId = r.getUserId();
//            SysUser sysUser = userMapper.selectById(userId);
//            if (ObjectUtil.isEmpty(sysUser)){
//                continue;
//            }else {
//                RepairUserVo repairUserVo = BeanCopyUtils.copyBean(sysUser, RepairUserVo.class);
//                r.setRepairUserVo(repairUserVo);
//            }
//        }
//        map.put("records", repairUserVos1);
//        return ResponseResult.okResult(map);
//    }
    }
}




