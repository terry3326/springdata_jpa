package com.itheima.test;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.dao.ArticleDao;
import com.itheima.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class SpringDataJpaTest {
	@Autowired
	private ArticleDao articleDao;

	// 查詢所有
	public void testFindAll() {
		List<Article> all = articleDao.findAll();
		for (Article article : all) {
			System.out.println(article);
		}
	}

}
