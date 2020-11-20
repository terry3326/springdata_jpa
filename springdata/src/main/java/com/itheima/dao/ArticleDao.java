package com.itheima.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itheima.domain.Article;

//自定義接口需要繼承JpaRepository<實體類的類型,實體類主鍵的類型>,JpaSpecificationExecutor<實體類的類型>
public interface ArticleDao extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

	// spring data 方法命名規則:藉由方法規則進行查詢(方法名有規定方式填寫) 缺點:查詢字串多的話會一大串+沒法傳入實體類
	// 根據標題查詢
	List<Article> findByTitle(String title);

	// 根據標題模糊查詢
	List<Article> findByTitleLike(String title);

	// 根據標題和作者查詢
	List<Article> findByTitleAndAuthor(String title, String Author);

	// 根據Id範圍查詢 > < between in

	List<Article> findByIdIsLessThan(Integer id);

	List<Article> findByIdBetween(Integer startId, Integer endId);

	List<Article> findByIdIn(List<Integer> Ids);

	// 根據創建時間之後查詢
	List<Article> findByCreatTimeAfter(Date creatTime);

	// 根據Id進行排序
	List<Article> findOrderByIdDesc();

///////////////////// JPQL查詢////////////////////////////////////////////////////////

	// JPQL:類似於SQL語句,但是要使用實體類名代替表名,使用屬性名代替字段名(面向對向查詢)

	// 展示位置參數綁定(按照title和author查詢)(不推薦使用)
	// 占位符從1開始
	@Query("from Article a where a.title = ?1 and a.author = ?2")
	List<Article> findByCondition1(String title, String author);

	// 展示名字參數綁定(推薦使用)
	@Query("from Article a where a.title = :title and a.author = :authors")
	List<Article> findByCondition2(@Param("title") String title, @Param("authors") String author);

	// 展示like模糊查詢
	@Query("from Article a where a.title like %:title% ")
	List<Article> findByCondition3(@Param("title") String title);

	// 展示排序查詢
	@Query("from Article a where a.title like %:title% order by a.id desc ")
	List<Article> findByCondition4(@Param("title") String title);

	// 展示分頁查詢
	@Query("from Article a where a.title like %:title%  ")
	List<Article> findByCondition5(Pageable pageable, @Param("title") String title);

	// 展示傳入集合參數查詢
	@Query("from Article a where a.title a.aid in aids  ")
	List<Article> findByCondition6(@Param("aids") List<Integer> aids);

	// 展示傳入Bean進行查詢(SPEL表達式查詢)固定寫法
	@Query("from Article a where a.title = :#{#article.title} and a.author = :#{#article.author}")
	List<Article> findByCondition7(@Param("article") Article article);

	////////////////// 本地SQL查詢///////////////////////////////////////////
	// 基本不使用,除非出現非常複雜的業務情況JPQL搞不定的時候 (因為這樣會造成跟數據庫耦合,不能直接更換數據庫種類)
	@Query(value = "select * from article where a.title = ?1 and a.author = ?2", nativeQuery = true)
	List<Article> findByCondition8(String title, String author);
}
