package cn.itcast.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml") //指定spring容器的配置信息
public class SpecTest {

    @Autowired(required = false)
    private CustomerDao customerDao;

    /**
     * 根據條件，查詢單個對象
     */
    @Test
    public void testSpec(){
        /**
         * 匿名內部類
         *  自定義查詢條件
         *          1.實現Specification接口(提供泛型，查詢的對象類型)
         *          2.實現toPredicate方法(構造查詢條件)
         *          3.需要借助方法參數中的兩個參數(
         *              root:獲取需要查詢的對象屬性
         *              CriteriaQuery:構造查詢條件的，內部封裝了很多的查詢條件(模糊匹配，精準匹配)
         *          )
         * 案例：根據客戶名稱查詢：查詢客戶名為張三的客戶
         *      查詢條件：
         *          1.查詢方法：
         *              cb對象
         *          2.比較的屬性名稱
         *              root對象
         */
        Specification<Customer> spec = new Specification<Customer>() {
            
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.獲取比較的屬性
                Path<Object> custName = root.get("custName");
                //2.構造查詢條件：  select * from cst_customer where cust_name = '李四'
                /**
                 * 第一個參數，需要比較的屬性(path對象)
                 * 第二個參數，當前需要比較的取值
                 */
                //進行精準的匹配(比較的屬性，比較的屬性的取值)
                Predicate predicate = cb.equal(custName, "張三");
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }
    /**
     * 多條件查詢
     *      案例：根據客戶名(張三)和客戶所屬行業查詢(哈哈哈)
     */
    @Test
    public void testSpec1() {
        /**
         * root:獲取屬性
         *      客戶名
         *      所屬行業
         * cb:構造查詢
         *      1.構造客戶名的精準匹配查詢
         *      2.構造所屬行業的精準匹配查詢
         *      3.將以上兩個查詢聯系起來
         */
        Specification<Customer> spec = new Specification<Customer>() {
            
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //客戶名
                Path<Object> custName = root.get("custName");
                //所屬行業
                Path<Object> custIndustry = root.get("custIndustry");
                //構造查詢
                //1.構造客戶名的精準匹配查詢
                //第一個參數，path(屬性)，第二個參數，屬性的取值
                Predicate p1 = cb.equal(custName, "張三");
                //2.構造所屬行業的精準匹配(=)查詢
                Predicate p2 = cb.equal(custIndustry, "哈哈哈");
                //3.將過個查詢條件組合到一起，組合(滿足條件一並且滿足條件二:與關系。滿足條件一或滿足條件二即可：或關系)
                Predicate and = cb.and(p1, p2);
                //cb.or();以或的形式拼接多個查詢條件
                return and;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 案例：完成根據客戶名稱的模糊匹配。返回客戶列表
     *      客戶名稱以"李四"開頭
     * equal：直接得到path對象(屬性)，然後進行比較即可
     * gt,lt,ge,le,like：得到path對象，根據path指定比較的參數類型，再去進行比較
     *      指定參數類型，path.as(類型的字節碼對象)
     */
    @Test
    public void testSpec3(){
        //構造查詢條件
        Specification<Customer> spec = new Specification<Customer>() {
          
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查詢屬性：客戶名
                Path<Object> custName = root.get("custName");
                //查詢方式：模糊匹配
                Predicate like = cb.like(custName.as(String.class), "李%");
                return like;
            }
        };
//        List<Customer> list = customerDao.findAll(spec);
//        for (Customer customer : list) {
//            System.out.println(customer);
//        }
        /**添加排序
         * 創建排序對象，需要調用構造方法實例化sort對象
         * 第一個參數：排序的順序(倒序，正序)
         *      Sort.Direction.DESC：倒序
         *      Sort.Direction.ASC：升序
         * 第二個參數：排序的屬性名稱
         */
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(spec, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 分頁查詢
     *      Specification：查詢條件
     *      Pageable：分頁參數
     *          分頁參數：查詢的頁碼，每頁查詢的條數
     *          findAll(Specification.Pageable)：帶有條件的分頁
     *          findAll(Pageable)：沒有條件的分頁
     * 返回：Page(SpringDataJpa為我們封裝好的pageBean對象，數據列表，共條數)
     */
    @Test
    public void testSpec4(){
        Specification spec = null;
        /**
         * PageRequest對象是Pageable接口的實現類
         * 創建PageRequest的過程，需要調用它的構造方法傳入兩個參數
         *      第一個參數：當前查詢的頁數(從0開始)
         *      第二個參數：每頁查詢的數量
         */
        Pageable pageable = new PageRequest(0,2);
        //分頁查詢
        Page<Customer> page = customerDao.findAll(null, pageable);
        System.out.println(page.getContent());//得到數據集合列表
        System.out.println(page.getTotalPages());//得到總頁數
        System.out.println(page.getTotalElements());//得到總條數
    }
}
