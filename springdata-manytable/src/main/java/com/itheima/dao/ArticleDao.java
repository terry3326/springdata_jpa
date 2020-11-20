package com.itheima.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.itheima.domain.Article;

public interface ArticleDao extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

}
