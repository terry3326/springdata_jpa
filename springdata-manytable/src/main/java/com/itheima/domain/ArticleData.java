package com.itheima.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "article_Data")
public class ArticleData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
	private Integer id;

	private String content;
	// 外鍵不用寫藉由註解設置

	/**
	 * 聲明類之間的關係 在類中使用註解再聲明表間關係 
	 * 1.書寫註解 
	 * 2.明確誰來維護關係 一對一和多對多:讓兩表中與其他表關係較少的負責維護
	 * 一對多:在多的地方維護關係 維護的一方主動聲明維護策略,在不維護的一方聲明主動放棄
	 */

	// name: 當前表中的外鍵名
	// referencedColumnName: 指向對方表中的主鍵名
	@OneToOne // 一對一要設置唯一約束 unique = true代表唯一
	@JoinColumn(name = "articleId", referencedColumnName = "id", unique = true)
	private Article article;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleData [id=" + id + ", content=" + content + "]";
	}

}
