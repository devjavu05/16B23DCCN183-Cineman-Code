package model;

import java.util.List;

import java.sql.Time;

/** Chi nhánh rạp. */
public class Branch3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private String phone;
    private Time openTime;
    private Time closeTime;
    private List<Auditorium3> auditoriums;

    public Branch3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Time getOpenTime() { return openTime; }
    public void setOpenTime(Time openTime) { this.openTime = openTime; }
    public Time getCloseTime() { return closeTime; }
    public void setCloseTime(Time closeTime) { this.closeTime = closeTime; }
    public List<Auditorium3> getAuditoriums() { return auditoriums; }
    public void setAuditoriums(List<Auditorium3> auditoriums) { this.auditoriums = auditoriums; }
}
