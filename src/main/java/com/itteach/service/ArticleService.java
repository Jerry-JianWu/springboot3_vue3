package com.itteach.service;

import com.itteach.pojo.Article;
import org.springframework.stereotype.Service;


public interface ArticleService {
    // 新增文章
    void add(Article article);
}
