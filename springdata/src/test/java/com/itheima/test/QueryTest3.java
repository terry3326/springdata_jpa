package com.itheima.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.dao.ArticleDao;
import com.itheima.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class QueryTest3 {
	@Autowired
	private ArticleDao ad;

	/**
	 * JPQL查詢 + 本地SQL查詢
	 */
	public void testfindByCondition1() {
		List<Article> list = ad.findByCondition1("IISI", "Terry");
	}

	public void testfindByCondition2() {
		List<Article> list = ad.findByCondition2("IISI", "Terry");
	}

	public void testfindByCondition3() {
		List<Article> list = ad.findByCondition3("IISI");
	}

	public void testfindByCondition4() {
		List<Article> list = ad.findByCondition4("IISI");
	}

	public void testfindByCondition5() {
		// page:當前第幾頁(從0開始) size:每頁大小
		Pageable pageable = PageRequest.of(0, 3);
		List<Article> list = ad.findByCondition5(pageable, "IISI");
	}

	public void testfindByCondition6() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(5);
		List<Article> article = ad.findByCondition6(list);
	}

	public void testfindByCondition7() {
		Article ac = new Article();
		ac.setTitle("IISI1");
		ac.setAuthor("Alston");
		List<Article> article = ad.findByCondition7(ac);
	}

	public void testfindByCondition8() {
		List<Article> list = ad.findByCondition8("IISI", "Terry");
	}
}
