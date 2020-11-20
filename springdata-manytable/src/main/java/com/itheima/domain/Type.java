package com.itheima.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
	private Integer tid;

	private String name;

	/**
	 * 聲明類之間的關係 在類中使用註解再聲明表間關係 1.書寫註解 2.明確誰來維護關係 一對一和多對多:讓兩表中與其他表關係較少的負責維護
	 * 一對多:在多的地方維護關係 在維護的一方主動聲明維護策略,在不維護的一方聲明主動放棄
	 */

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			// 中間表名
			name = "article_type",
			// 中間表的外鍵對應到當前表的主鍵名稱
			joinColumns = { @JoinColumn(name = "tid", referencedColumnName = "tid") },
			// 中間表的外鍵對應到對方表的主鍵名稱
			inverseJoinColumns = { @JoinColumn(name = "id", referencedColumnName = "id") })
	private Set<Article> article = new HashSet<Article>();

	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Type [tid=" + tid + ", name=" + name + "]";
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
