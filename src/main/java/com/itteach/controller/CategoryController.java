package com.itteach.controller;

import com.itteach.pojo.Category;
import com.itteach.pojo.Result;
import com.itteach.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated Category category){
        categoryService.add(category);
        return Result.success();

    }
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }
}
