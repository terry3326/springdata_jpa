package com.itheima.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.dao.ArticleDao;
import com.itheima.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class QueryTest1 {
	@Autowired
	private ArticleDao ad;

	// 根據主鍵進行查詢
	public void testFindById() {
		// 根據主鍵查詢
		Optional<Article> findById = ad.findById(1);
		// 根據多個主鍵查詢
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		List<Article> findAllById = ad.findAllById(list);

	}

	// 查詢所有
	public void testFindAll() {
		List<Article> findAll = ad.findAll();
	}

	// 查詢所有--排序
	public void testFindWithSort() {
		// 按照id倒序排列
		Sort sort = Sort.by(Sort.Order.desc("id"));
		List<Article> findAll = ad.findAll(sort);
	}

	// 查詢所有--分頁
	public void testFindWithPage() {
		// 處理分頁條件
		// page:當前第幾頁(從0開始) size:每頁大小
		Pageable pageable = PageRequest.of(3, 2);
		Page<Article> page = ad.findAll(pageable);

		// 總紀錄數 總頁數 每頁多少
		System.out.println("總紀錄數" + page.getTotalElements());
		System.out.println("總頁數" + page.getTotalPages());
		System.out.println("每頁多少" + page.getSize());
		// 當頁元素
		List<Article> content = page.getContent();
		for (Article article : content) {
			System.out.println(article);
		}
	}

	// 查詢所有--排序+分頁
	public void testFindWithSortAndPage() {
		// 按照id倒序排列
		Sort sort = Sort.by(Sort.Order.desc("id"));

		// 處理分頁條件
		// page:當前第幾頁(從0開始) size:每頁大小
		Pageable pageable = PageRequest.of(3, 2, sort);
		Page<Article> page = ad.findAll(pageable);

		// 總紀錄數 總頁數 每頁多少
		System.out.println("總紀錄數" + page.getTotalElements());
		System.out.println("總頁數" + page.getTotalPages());
		System.out.println("每頁多少" + page.getSize());
		// 當頁元素
		List<Article> content = page.getContent();
		for (Article article : content) {
			System.out.println(article);
		}
	}
}
