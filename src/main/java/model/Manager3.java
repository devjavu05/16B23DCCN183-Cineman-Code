package model;

/** Quản lí rạp. */
public class Manager3 extends Employee3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String position;
    private String scope;

    public Manager3() {}

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getScope() { return scope; }
    public void setScope(String scope) { this.scope = scope; }
}
