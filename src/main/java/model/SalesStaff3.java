package model;

/** Nhân viên bán vé. */
public class SalesStaff3 extends Employee3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String position;

    public SalesStaff3() {}

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}
