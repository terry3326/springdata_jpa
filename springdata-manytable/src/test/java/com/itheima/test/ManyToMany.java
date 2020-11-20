package com.itheima.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.dao.ArticleDao;
import com.itheima.dao.TypeDao;
import com.itheima.domain.Article;
import com.itheima.domain.Type;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ManyToMany {
	@Autowired
	private ArticleDao articleDao;
	private TypeDao typeDao;

	// 保存
	@Test
	public void testSave() {
		// 創建文章對象
		Article article1 = new Article();
		article1.setTitle("多對多3");
		article1.setAuthor("Terry3");
		article1.setCreatTime(new Date());

		Article article2 = new Article();
		article2.setTitle("多對多4");
		article2.setAuthor("Terry4");
		article2.setCreatTime(new Date());

		// 創建文章類型對象
		Type type1 = new Type();
		type1.setName("娛樂");
		Type type2 = new Type();
		type2.setName("財經");

		// 建立兩者關係
		Set<Type> types = new HashSet<Type>();
		types.add(type1);
		types.add(type2);
		article1.setType(types);
		article2.setType(types);

		Set<Article> articles = new HashSet<Article>();
		articles.add(article1);
		articles.add(article2);
		type1.setArticle(articles);
		type2.setArticle(articles);

		// 保存操作
		articleDao.save(article1);
		articleDao.save(article2);
	

	}

}
