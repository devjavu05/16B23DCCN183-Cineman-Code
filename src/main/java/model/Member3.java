package model;

import java.sql.Date;

/** Thành viên đăng kí tài khoản. Java đơn kế thừa nên Member3 kế thừa User3 và chứa Customer3 qua thuộc tính customer (khớp bảng tblMember3: username + tblCustomer3id). */
public class Member3 extends User3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Customer3 customer;
    private int loyaltyPoints;
    private Date registerDate;
    private double totalSpent;

    public Member3() {}

    public Customer3 getCustomer() { return customer; }
    public void setCustomer(Customer3 customer) { this.customer = customer; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }
    public Date getRegisterDate() { return registerDate; }
    public void setRegisterDate(Date registerDate) { this.registerDate = registerDate; }
    public double getTotalSpent() { return totalSpent; }
    public void setTotalSpent(double totalSpent) { this.totalSpent = totalSpent; }
}
