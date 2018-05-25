package com.yang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.mapper.ArticleMapper;
import com.yang.pojo.Article;
import com.yang.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleMapper articleMapper;
	@Override
	public List<Article> getArticleList() {
		// TODO Auto-generated method stub
		return articleMapper.getArticleList();
	}

	@Override
	public Article getArticleByArticleid(String articleid) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleByArticleid(articleid);
	}

	@Override
	public List<Article> getArticleByUserid(String userid) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleByUserid(userid);
	}

	@Override
	public List<Article> getArticleByType(String type) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleByType(type);
	}

	@Override
	public int addArticle(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.addArticle(article);
	}

	@Override
	public int updateArticle(Article Article) {
		// TODO Auto-generated method stub
		return articleMapper.updateArticle(Article);
	}

	@Override
	public int delArticle(int articleid) {
		// TODO Auto-generated method stub
		return articleMapper.delArticle(articleid);
	}

}
