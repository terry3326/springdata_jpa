package cn.itcast.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一個客戶，保存一個聯系人
     *  效果：客戶和聯系人作為獨立的數據保存到數據庫中
     *      聯系人的外鍵為空
     *  原因？
     *      實體類中沒有配置關系
     */
    @Test
    @Transactional //配置事務
    @Rollback(false) //不自動回滾
    public void testAdd() {
        //創建一個客戶，創建一個聯系人
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        /**
         * 配置了客戶到聯系人的關系
         *      從客戶的角度上：发送兩條insert語句，发送一條更新語句更新數據庫（更新外鍵）
         * 由於我們配置了客戶到聯系人的關系：客戶可以對外鍵進行維護
         */
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional //配置事務
    @Rollback(false) //不自動回滾
    public void testAdd1() {
        //創建一個客戶，創建一個聯系人
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");
        /**
         * 配置聯系人到客戶的關系（多對一）
         *    只发送了兩條insert語句
         * 由於配置了聯系人到客戶的映射關系（多對一）
         */
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 會有一條多余的update語句
     *      * 由於一的一方可以維護外鍵：會发送update語句
     *      * 解決此問題：只需要在一的一方放棄維護權即可
     *
     */
    @Test
    @Transactional //配置事務
    @Rollback(false) //不自動回滾
    public void testAdd2() {
        //創建一個客戶，創建一個聯系人
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        linkMan.setCustomer(customer);//由於配置了多的一方到一的一方的關聯關系（當保存的時候，就已經對外鍵賦值）
        customer.getLinkMans().add(linkMan);//由於配置了一的一方到多的一方的關聯關系（发送一條update語句）

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 級聯添加：保存一個客戶的同時，保存客戶的所有聯系人
     *      需要在操作主體的實體類上，配置casacde屬性
     */
    @Test
    @Transactional //配置事務
    @Rollback(false) //不自動回滾
    public void testCascadeAdd() {
        Customer customer = new Customer();
        customer.setCustName("百度1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李1");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
    }

    /**
     * 級聯刪除：
     *      刪除1號客戶的同時，刪除1號客戶的所有聯系人
     */
    @Test
    @Transactional //配置事務
    @Rollback(false) //不自動回滾
    public void testCascadeRemove() {
        //1.查詢1號客戶
        Customer customer = customerDao.findOne(1l);
        //2.刪除1號客戶
        customerDao.delete(customer);
    }
}
