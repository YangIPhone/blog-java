package com.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.Article;

public interface ArticleMapper {
	public List<Article> getArticleList();
	public Article getArticleByArticleid(@Param("articleid") String articleid);
	public List<Article> getArticleByUserid(@Param("userid") String userid);
	public List<Article> getArticleByType(@Param("type") String type);
	public int addArticle(Article article);
    public int updateArticle(Article Article);
    public int delArticle(@Param("articleid") int articleid);
}
