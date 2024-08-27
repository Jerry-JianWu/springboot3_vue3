package com.itteach.mapper;

import com.itteach.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    // 新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "value(#{title}, #{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    // 动态查询sql，使用映射配置文件

    List<Article> list(Integer userId, Integer categoryId, String state);
    @Select("select * from article where id=#{id}")
    Article findById(Integer id);

    @Update("update article set title=#{title}, content=#{content}, cover_img=#{coverImg},state=#{state},category_id=#{categoryId} where id =#{id}")
    void update(Article article);
}
