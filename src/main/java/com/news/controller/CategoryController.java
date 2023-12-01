package com.news.controller;


import com.news.domain.ResponseResult;
import com.news.domain.entity.Category;
import com.news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("add")
    public ResponseResult insert(@RequestBody Category category){
        boolean save = categoryService.save(category);
        if (save){
            return ResponseResult.okResult();
        }return ResponseResult.errorResult(202,"网络错误");
    }
    @GetMapping("all")
    public ResponseResult getList(){
        return ResponseResult.okResult(categoryService.list());
    }
    @GetMapping("/delete")
    public ResponseResult delete(int id){
        boolean b = categoryService.removeById(id);
        if (b){
            return ResponseResult.okResult();
        }return ResponseResult.errorResult(202,"网络错误");
    }
}
