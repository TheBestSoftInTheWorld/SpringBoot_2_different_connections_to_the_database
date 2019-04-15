package com.example.springbootapp.jpa.service;

import java.util.List;
import com.example.springbootapp.jpa.model.Article;

public interface IArticleService {
    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    boolean addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
}
