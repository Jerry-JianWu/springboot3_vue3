package com.itteach.controller;

import com.itteach.pojo.Article;
import com.itteach.pojo.Result;
import com.itteach.service.ArticleService;
import com.itteach.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return Result.success();

    }

}
