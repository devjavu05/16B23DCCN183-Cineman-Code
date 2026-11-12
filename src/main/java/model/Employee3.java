package model;

import java.sql.Date;

/** Nhân viên (lớp trừu tượng) kế thừa User3. */
public abstract class Employee3 extends User3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String fullName;
    private String phone;
    private String email;
    private Date hireDate;
    private Branch3 branch;

    public Employee3() {}

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
    public Branch3 getBranch() { return branch; }
    public void setBranch(Branch3 branch) { this.branch = branch; }
}
