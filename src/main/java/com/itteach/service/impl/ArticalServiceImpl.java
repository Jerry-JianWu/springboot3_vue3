package com.itteach.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itteach.mapper.ArticleMapper;
import com.itteach.pojo.Article;
import com.itteach.pojo.PageBean;
import com.itteach.service.ArticleService;
import com.itteach.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticalServiceImpl implements ArticleService {
    //
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1. 创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        // 2. 开启分页查询
        PageHelper.startPage(pageNum,pageSize);

        // 3.调用mapper
        // 已登录用户只能查询自己的文章
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        List<Article> as = articleMapper.list(userId, categoryId, state);
        // Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据,
        // 查询到的结果是List集合，得向下强转为Page对象集合才能调用Page对象中的方法获得总数和条数
        // 不强转的话多态是不允许父类调用子类方法的
        Page<Article> p = (Page<Article>) as;

        // 填充到PageBean中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Article findById(Integer id) {
        Article article = articleMapper.findById(id);
        return article;
    }


}
