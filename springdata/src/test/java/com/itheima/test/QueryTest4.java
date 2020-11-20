package com.itheima.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.itheima.dao.ArticleDao;
import com.itheima.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class QueryTest4 {
	@Autowired
	private ArticleDao ad;

	/**
	 * 動態查詢:Dao接口必須繼承JpaSpecificationExecutor接口才可以
	 */

	// 按照作者和標題進行查詢,以不為空的屬性作為查詢條件,如果都為空則查詢所有
	public void testfindAll(final String title, final String author) {
		// 模擬從外邊傳來的變量

		List<Article> list = ad.findAll(new Specification<Article>() {
			/**
			 * @parm root 代表實體對象,我們可以通過他獲取屬性值
			 * @parm cq 用於生成SQL語句
			 * @parm cb 用於拼接查詢條件
			 */
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(title)) {
					// 拼接作為查詢條件 尋找title屬性值為變量title的Article
					Predicate predicate = cb.equal(root.get("title").as(String.class), title);
					list.add(predicate);
				}
				if (!StringUtils.isEmpty(author)) {
					// 拼接作為查詢條件 尋找author屬性值為變量author的Article
					Predicate predicate = cb.equal(root.get("author").as(String.class), author);
					list.add(predicate);
				}
				// 設定兩者間的關係
				Predicate predicate = cb.and(list.toArray(new Predicate[] {}));

				return predicate;
			}
		});
		for (Article article : list) {
			System.out.println(article);
		}
	}

	public void testfindAllWithPage(final String title, final String author) {
		// 模擬從外邊傳來的變量
		// 分頁
		Pageable pageable = PageRequest.of(0, 3);

		Page<Article> findAll = ad.findAll(new Specification<Article>() {
			/**
			 * @parm root 代表實體對象,我們可以通過他獲取屬性值
			 * @parm cq 用於生成SQL語句
			 * @parm cb 用於拼接查詢條件
			 */
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(title)) {
					// 拼接作為查詢條件 尋找title屬性值為變量title的Article
					Predicate predicate = cb.equal(root.get("title").as(String.class), title);
					list.add(predicate);
				}
				if (!StringUtils.isEmpty(author)) {
					// 拼接作為查詢條件 尋找author屬性值為變量author的Article
					Predicate predicate = cb.equal(root.get("author").as(String.class), author);
					list.add(predicate);
				}
				// 設定兩者間的關係
				Predicate predicate = cb.and(list.toArray(new Predicate[] {}));

				return predicate;
			}
		}, pageable);
		for (Article article : findAll.getContent()) {
			System.out.println(article);
		}
	}

	public void testfindAllWithPageAndSort(final String title, final String author) {
		// 模擬從外邊傳來的變量
		// 分頁 + 以id排序
		Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Order.desc("id")));
//		Pageable pageable = PageRequest.of(0, 3, Direction.DESC,"id");同上

		Page<Article> findAll = ad.findAll(new Specification<Article>() {
			/**
			 * @parm root 代表實體對象,我們可以通過他獲取屬性值
			 * @parm cq 用於生成SQL語句
			 * @parm cb 用於拼接查詢條件
			 */
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(title)) {
					// 拼接作為查詢條件 尋找title屬性值為變量title的Article
					Predicate predicate = cb.equal(root.get("title").as(String.class), title);
					list.add(predicate);
				}
				if (!StringUtils.isEmpty(author)) {
					// 拼接作為查詢條件 尋找author屬性值為變量author的Article
					Predicate predicate = cb.equal(root.get("author").as(String.class), author);
					list.add(predicate);
				}
				// 設定兩者間的關係
				Predicate predicate = cb.and(list.toArray(new Predicate[] {}));

				return predicate;
			}
		}, pageable);
		for (Article article : findAll.getContent()) {
			System.out.println(article);
		}
	}

}
