package com.itheima.test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
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

	// 保存
	@Test
	public void testSave() {
		Article article = new Article();
		article.setTitle("IISI");
		article.setAuthor("Terry");
		article.setCreatTime(new Date());

		articleDao.save(article);
	}

	// 查詢主鍵
	public void testFindByAid() {
		Optional<Article> optional = articleDao.findById(1);
		System.out.println(optional);
	}

	// 查詢所有
	public void testFindAll() {
		List<Article> all = articleDao.findAll();
		for (Article article : all) {
			System.out.println(article);
		}
	}

	// 修改
	public void testUpdate() {
		Optional<Article> optional = articleDao.findById(1);
		Article article = optional.get();
		article.setAuthor("Alston");
		// springdata jpa的保存和修改都是使用save()方法
		// 關鍵來看傳入的實體是否有主鍵
		// 如果有主鍵,代表要修改 沒有代表要保存
		articleDao.save(article);
	}

	// 刪除
	public void testDelete() {
		articleDao.deleteById(1);
	}

}
