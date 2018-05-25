package com.yang.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.Article;

public interface ArticleService {
	public List<Article> getArticleList();
	public Article getArticleByArticleid(String articleid);
	public List<Article> getArticleByUserid(String userid);
	public List<Article> getArticleByType(String type);
	public int addArticle(Article article);
    public int updateArticle(Article Article);
    public int delArticle(int articleid);
}
