package com.itheima.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
	private Integer cid;

	private String comment;

	/**
	 * 聲明類之間的關係 在類中使用註解再聲明表間關係 1.書寫註解 2.明確誰來維護關係 一對一和多對多:讓兩表中與其他表關係較少的負責維護
	 * 一對多:在多的地方維護關係 在維護的一方主動聲明維護策略,在不維護的一方聲明主動放棄
	 */
	// name: 當前表中的外鍵名
	// referencedColumnName: 指向對方表中的主鍵名
	@ManyToOne
	@JoinColumn(name = "aid", referencedColumnName = "id")
	private Article article;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", comment=" + comment + "]";
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
