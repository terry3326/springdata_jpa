package cn.itcast.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class) // 聲明spring提供的單元測試環境
@ContextConfiguration(locations = "classpath:applicationContext.xml") // 指定spring容器的配置信息
public class CustomerDaoTest {
	@Autowired(required = false)
	private CustomerDao customerDao;

	// 根據id查詢
	@Test
	public void testFindOne() {
		Customer customer = customerDao.findOne(3l);
		System.out.println(customer);
	}

	/**
	 * save:保存或者更新 根據傳遞的對象是否存在主鍵id 如果沒有id主鍵屬性，保存 存在id主鍵屬性，根據id查詢數據，更新數據
	 */
	@Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setCustName("趙六");
		customer.setCustLevel("vip");
		customer.setCustIndustry("哈哈哈哈");
		customerDao.save(customer);
	}

	@Test
	public void testUpdate() {
		Customer customer = new Customer();
		customer.setCustId(4l);
		customer.setCustName("趙六");
		customer.setCustIndustry("紅紅火火恍恍惚惚");
		customerDao.save(customer);
	}

	@Test
	public void testDelete() {
		customerDao.delete(3l);
	}

	// 查詢所有
	@Test
	public void testFindAll() {
		List<Customer> list = customerDao.findAll();
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}

	// 測試統計查詢，查詢客戶的總數量
	@Test
	public void testCount() {
		// 查詢全部的客戶數量
		long count = customerDao.count();
		System.out.println(count);
	}

	// 判斷id為4的客戶是否存在
	@Test
	public void testExists() {
		boolean exists = customerDao.exists(4l);
		System.out.println("id為4的客戶是否存在：" + exists);
	}

	/*
	 * 根據id從數據庫查詢 findOne em.find(): 立即加載 getOne em.getReference: 延遲加載 *
	 * 返回的是一個客戶的動態代理對象 * 什麽時候用，什麽時候查詢
	 */
	@Test
	@Transactional // 保證getOne正常運行
	public void testGetOne() {
		Customer customer = customerDao.getOne(4l);
		System.out.println(customer);
	}
}
