package model;

import java.sql.Date;

/** Khách hàng. Thẻ thành viên (card) được lưu thành các cột thẻ của bảng tblCustomer3. */
public class Customer3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String fullName;
    private Date birthDate;
    private String phone;
    private String email;
    private String gender;
    private String address;
    private Date createdDate;
    private MembershipCard3 card;

    public Customer3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public MembershipCard3 getCard() { return card; }
    public void setCard(MembershipCard3 card) { this.card = card; }
}
