package cn.itcast.test;


import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    //could not initialize proxy - no Session
    //測試對象導航查詢（查詢一個對象的時候，通過此對象查詢所有的關聯對象）
    @Test
    @Transactional // 解決在java代碼中的no session問題
    public void  testQuery1() {
        //查詢id為1的客戶
        Customer customer = customerDao.getOne(1l);
        //對象導航查詢，此客戶下的所有聯系人
        Set<LinkMan> linkMans = customer.getLinkMans();

        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }
    }

    /**
     * 對象導航查詢：
     *      默認使用的是延遲加載的形式查詢的
     *          調用get方法並不會立即发送查詢，而是在使用關聯對象的時候才會差和訊
     *      延遲加載！
     * 修改配置，將延遲加載改為立即加載
     *      fetch，需要配置到多表映射關系的注解上
     */
    @Test
    @Transactional // 解決在java代碼中的no session問題
    public void  testQuery2() {
        //查詢id為1的客戶
        Customer customer = customerDao.findOne(1l);
        //對象導航查詢，此客戶下的所有聯系人
        Set<LinkMan> linkMans = customer.getLinkMans();

        System.out.println(linkMans.size());
    }

    /**
     * 從聯系人對象導航查詢他的所屬客戶
     *      * 默認 ： 立即加載
     *  延遲加載：
     */
    @Test
    @Transactional // 解決在java代碼中的no session問題
    public void  testQuery3() {
        LinkMan linkMan = linkManDao.findOne(2l);
        //對象導航查詢所屬的客戶
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}
