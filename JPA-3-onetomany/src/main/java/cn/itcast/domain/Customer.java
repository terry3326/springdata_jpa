package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 1.實體類和表的映射關系
 *      @Entity
 *      @Table
 * 2.類中屬性和表中字段的映射關系
 *      @Id
 *      @GeneratedValue
 *      @Column
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id//聲明當前私有屬性為主鍵
    @GeneratedValue(strategy= GenerationType.IDENTITY) //配置主鍵的生成策略
    @Column(name="cust_id") //指定和表中cust_id字段的映射關系
    private Long custId;

    @Column(name="cust_name") //指定和表中cust_name字段的映射關系
    private String custName;

    @Column(name="cust_source")//指定和表中cust_source字段的映射關系
    private String custSource;

    @Column(name="cust_industry")//指定和表中cust_industry字段的映射關系
    private String custIndustry;

    @Column(name="cust_level")//指定和表中cust_level字段的映射關系
    private String custLevel;

    @Column(name="cust_address")//指定和表中cust_address字段的映射關系
    private String custAddress;

    @Column(name="cust_phone")//指定和表中cust_phone字段的映射關系
    private String custPhone;

    //配置客戶和聯系人之間的關系（一對多關系）
    /**
     * 使用注解的形式配置多表關系
     *      1.聲明關系
     *          @OneToMany : 配置一對多關系
     *              targetEntity ：對方對象的字節碼對象
     *      2.配置外鍵（中間表）
     *              @JoinColumn : 配置外鍵
     *                  name：外鍵字段名稱
     *                  referencedColumnName：參照的主表的主鍵字段名稱
     * 在客戶實體類上（一的一方）添加了外鍵了配置，所以對於客戶而言，也具備了維護外鍵的作用
     */

//    @OneToMany(targetEntity = LinkMan.class)
//    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    /**
     * 放棄外鍵維護權
     *      mappedBy："當前類在對方類中的屬性名"
     * cascade : 配置級聯（可以配置到設置多表的映射關系的注解上）
     *      CascadeType.all         : 所有
     *                  MERGE       ：更新
     *                  PERSIST     ：保存
     *                  REMOVE      ：刪除
     *
     * fetch : 配置關聯對象的加載方式
     *          EAGER   ：立即加載
     *          LAZY    ：延遲加載
     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }
}
