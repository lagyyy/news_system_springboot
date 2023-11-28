package com.news.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.entity.Category;
import com.news.service.CategoryService;
import com.news.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author ngz
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




