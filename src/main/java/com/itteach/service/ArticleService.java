package com.itteach.service;

import com.itteach.pojo.Article;
import com.itteach.pojo.PageBean;
import org.springframework.stereotype.Service;


public interface ArticleService {
    // 新增文章
    void add(Article article);

    // 文章列表分页
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 获取文章信息
    Article findById(Integer id);

    // 更新文章
    void update(Article article);
}
