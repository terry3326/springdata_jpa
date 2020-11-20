package com.itheima.test;

import java.util.Date;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.dao.ArticleDao;
import com.itheima.dao.CommentDao;
import com.itheima.domain.Article;
import com.itheima.domain.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OneToMany {
	@Autowired
	private ArticleDao articleDao;
	private CommentDao commentDao;

	// 保存
	@Test
	public void testSave() {
		// 創建文章對象
		Article article = new Article();
		article.setTitle("一對多");
		article.setAuthor("Terry");
		article.setCreatTime(new Date());

		// 創建文章評論對象
		Comment comment1 = new Comment();
		comment1.setComment("Good");
		Comment comment2 = new Comment();
		comment2.setComment("Perfect");

		// 建立兩者關係
		comment1.setArticle(article);
		comment2.setArticle(article);

		HashSet<Comment> comments = new HashSet<Comment>();
		comments.add(comment1);
		comments.add(comment2);
		article.setComments(comments);

		// 保存操作
		articleDao.save(article);
	
	}

}
