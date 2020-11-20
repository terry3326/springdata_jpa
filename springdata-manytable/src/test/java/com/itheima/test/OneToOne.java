package com.itheima.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.dao.ArticleDao;
import com.itheima.domain.Article;
import com.itheima.domain.ArticleData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OneToOne {
	@Autowired
	private ArticleDao articleDao;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArticleDao articleDao = c.getBean(ArticleDao.class);
		// 創建文章對象
		Article article = new Article();
		article.setTitle("JPA測試一");
		article.setAuthor("Terry");
		article.setCreatTime(new Date());

		// 創建文章內容對象
		ArticleData articleData = new ArticleData();
		articleData.setContent("真的累");

		// 建立兩者關係
		article.setArticleData(articleData);
		articleData.setArticle(article);

		// 保存操作
		articleDao.save(article);
	}

	// 保存
	@Test
	public void testSave() {
		// 創建文章對象
		Article article = new Article();
		article.setTitle("JPA測試一");
		article.setAuthor("Terry");
		article.setCreatTime(new Date());

		// 創建文章內容對象
		ArticleData articleData = new ArticleData();
		articleData.setContent("真的累");

		// 建立兩者關係
		article.setArticleData(articleData);
		articleData.setArticle(article);

		// 保存操作
		articleDao.save(article);
	}

}
