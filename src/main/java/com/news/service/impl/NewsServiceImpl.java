package com.news.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.domain.ResponseResult;
import com.news.domain.entity.Admin;
import com.news.domain.entity.Category;
import com.news.domain.entity.News;
import com.news.domain.query.NewsQuery;
import com.news.domain.vo.NewsVo;
import com.news.mapper.AdminMapper;
import com.news.mapper.CategoryMapper;
import com.news.service.CategoryService;
import com.news.service.NewsService;
import com.news.mapper.NewsMapper;
import com.news.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author ngz
* @description 针对表【news】的数据库操作Service实现
* @createDate 2023-11-28 16:31:38
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService {
    @Autowired
    AdminMapper adminMapper;


    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ResponseResult getNewsListPage(Long current, Long limit, NewsQuery newsQuery) {
        Page<News> newsPage = new Page<>(current, limit);
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.eq("flag_delete","0");
        wrapper.orderByDesc("publish_date");
        if (newsQuery != null) {
            String begin = newsQuery.getBegin();
            String end = newsQuery.getEnd();
            if (!StringUtils.isEmpty(begin) && !StringUtils.isEmpty(end)) {
                wrapper.between("publish_date", begin, end);
            }
            Integer categoryId = newsQuery.getCategoryId();
            if (ObjectUtil.isNotNull(categoryId)){
                wrapper.eq("category_id",categoryId);
                page(newsPage, wrapper);
                long total = newsPage.getTotal();
                List<News> records = newsPage.getRecords();
                List<NewsVo> newsVos = BeanCopyUtils.copyBeanList(records, NewsVo.class);

                for (NewsVo newsVo:newsVos){
                    Admin admin = adminMapper.selectById(newsVo.getAdminId());
                    if (ObjectUtil.isNotNull(admin)){
                        newsVo.setAvatar(admin.getAvatar());
                        newsVo.setNickName(admin.getNickName());
                    }
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("total", total);
                map.put("records", newsVos);
                return ResponseResult.okResult(map);
            }else {
                QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
                List<Category> categories = categoryMapper.selectList(categoryQueryWrapper);
                List<List> lists = new ArrayList<>();
                for (Category category:categories){
                    wrapper.orderByDesc("publish_date");
                    System.out.println(category.getId()+"_____________________________________");
                    wrapper.eq("category_id",category.getId());
                    System.out.println(category.getId()+"_____________________________________");
                    page(newsPage, wrapper);
                    long total = newsPage.getTotal();
                    List<News> records = newsPage.getRecords();
                    List<NewsVo> newsVos = BeanCopyUtils.copyBeanList(records, NewsVo.class);
                    for (NewsVo newsVo:newsVos){
                        newsVo.setContent(newsVo.getContent().substring(0,100));
                        Admin admin = adminMapper.selectById(newsVo.getAdminId());
                        if (ObjectUtil.isNotNull(admin)){
                            newsVo.setAvatar(admin.getAvatar());
                            newsVo.setNickName(admin.getNickName());
                        }

                    }
                    lists.add(newsVos);
                    wrapper.clear();
                }

            return ResponseResult.okResult(lists);
            }


        }


           return null;

    }

    @Override
    public ResponseResult getNewsOne(Long id) {
        News byId = getById(id);
        NewsVo newsVo = BeanCopyUtils.copyBean(byId, NewsVo.class);
        Admin admin = adminMapper.selectById(newsVo.getAdminId());
        newsVo.setAdminId(admin.getId());
        newsVo.setAvatar(admin.getAvatar());
        newsVo.setNickName(admin.getNickName());
        return ResponseResult.okResult(newsVo);
    }
}




