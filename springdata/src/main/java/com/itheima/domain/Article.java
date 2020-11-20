package com.itheima.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 告訴JAP這是一個實體類,需要把他跟數據庫中的表作映射
//使用註解建立實體類與數據庫表之間的對應關係
@Table(name = "article") // 建立了實體淚與數據表的關係 name指向表名 不寫默認為類名(開頭小寫)
public class Article {
	@Id // 這是主鍵字段
		// 主見生成策略
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
	private Integer id;

	// 使用@Column映射類的屬性和數據表的字段關係 name指定表中的字段名
	// 當類的屬性名和數據表中字段名一致時,此註解可省略
	@Column(name = "author")
	private String author;

	private Date creatTime;

	private String title;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", creatTime=" + creatTime + ", title=" + title + "]";
	}

}
