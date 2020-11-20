package com.itheima.test;

import java.util.ArrayList;
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
public class SpringDataJpaCudTest {
	@Autowired
	private ArticleDao articleDao;

	// 保存
	@Test
	public void testSave() {
		Article article = new Article();
		article.setTitle("IISI");
		article.setAuthor("Terry");
		article.setCreatTime(new Date());
		// 保存一個實體
		articleDao.save(article);
		// 保存一個實體,並且立即刷新緩存
//		articleDao.saveAndFlush(article);
	}

	// 保存多個
	@Test
	public void testSaveAll() {
		Article article = new Article();
		article.setTitle("IISI");
		article.setAuthor("Terry");
		article.setCreatTime(new Date());
		ArrayList<Article> list = new ArrayList<Article>();
		list.add(article);
		// 保存多個實體
		articleDao.saveAll(list);
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

	// 刪除
	public void testDeleteOne() {
		// 根據實體刪除,但是這個實體必須要有主鍵
		Article article = new Article();
		article.setId(1);
		articleDao.delete(article);
	}

	// 刪除
	public void testDeleteAll() {
		// 刪除所有 先查詢再逐一刪除
		articleDao.deleteAll();
		// 刪除所有 直接刪除表中數據
		articleDao.deleteAllInBatch();

		Article article = new Article();
		article.setId(1);
		ArrayList<Article> list = new ArrayList<Article>();
		// 批量刪除指定數據 一條語句搞定
		articleDao.deleteInBatch(list);

		// 先逐一查詢,再逐一刪除
		articleDao.deleteAll(list);
	}

}
