package model;

/** Tài khoản đăng nhập (lớp cha trừu tượng của Member3 và Employee3). */
public abstract class User3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String status;

    public User3() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
