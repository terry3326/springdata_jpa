package com.itheima.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.dao.ArticleDao;
import com.itheima.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class QueryTest2 {
	@Autowired
	private ArticleDao ad;

	/**
	 * 方法命名規則查詢
	 */
	public void testFindBytitle() {
		List<Article> title = ad.findByTitle("IISI");
	}

	public void testFindBytitleLike() {
		List<Article> title = ad.findByTitleLike("%IISI%");
	}

	public void testFindByTitleAndAuthor() {
		List<Article> title = ad.findByTitleAndAuthor("IISI", "Terry");
	}

	public void testfindOrderByIdDesc() {
		List<Article> title = ad.findOrderByIdDesc();
	}

	public void testfindByIdIsLessThan() {
		List<Article> title = ad.findByIdIsLessThan(2);
	}

	public void findByIdBetween() {
		List<Article> title = ad.findByIdBetween(1, 5);
	}

	public void testfindByIdIn() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(5);
		List<Article> title = ad.findByIdIn(list);
	}

	public void testfindByCreatTimeAfter() {
		List<Article> title = ad.findByCreatTimeAfter(new Date());
	}
}
