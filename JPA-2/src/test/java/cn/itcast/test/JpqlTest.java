package cn.itcast.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class) // 聲明spring提供的單元測試環境
@ContextConfiguration(locations = "classpath:applicationContext.xml") // 指定spring容器的配置信息
public class JpqlTest {

	@Autowired(required = false)
	private CustomerDao customerDao;

	@Test
	public void testFindJPQL() {
		Customer customer = customerDao.findJpql("張三");
		System.out.println(customer);
	}

	@Test
	public void testFindCustNameAndId() {
		Customer customer = customerDao.findCustNameAndId(5l, "李四");
		System.out.println(customer);
	}

	/**
	 * 測試jpql的更新操作
	 * SpringDataJpa中使用jpql完成 更新/刪除操作 
	 * 需要手動添加事務的支持 默認會執行結束後，回滾事務
	 * @Rollback：設置是否自動回滾 false | true
	 */
	@Test
	@Transactional // 添加事務的支持
	@Rollback(value = false)
	public void testUpdateCustomer() {
		customerDao.updateCustomer(4l, "王五");
	}

	// 測試sql查詢
	@Test
	public void testFindSql() {
		List<Object[]> list = customerDao.findSql("李%");
		for (Object[] obj : list) {
			System.out.println(Arrays.toString(obj));
		}
	}

	// 測試方法命名規則的查詢
	@Test
	public void testNaming() {
		Customer customer = customerDao.findByCustName("張三");
		System.out.println(customer);
	}

	// 測試方法命名規則的查詢
	@Test
	public void testFindByCustNameLike() {
		List<Customer> list = customerDao.findByCustNameLike("李%");
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}

	// 測試方法命名規則的查詢
	@Test
	public void testFindByCustNameLikeAndCustIndustry() {
		Customer customer = customerDao.findByCustNameLikeAndCustIndustry("李%", "嘿嘿");
		System.out.println(customer);
	}
}
