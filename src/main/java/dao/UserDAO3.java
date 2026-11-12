package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Employee3;
import model.Manager3;
import model.Member3;
import model.User3;

/** DAO dang nhap (use case Login, ngoai pham vi 2 module nhung can de bao ve cac trang). */
public class UserDAO3 extends DAO3 {

    /** Tra ve Manager3 / Member3 neu dung tai khoan va mat khau, nguoc lai null. */
    public User3 login(String username, String password) {
        String sql = "SELECT u.username, u.status, "
                + "(SELECT COUNT(*) FROM tblManager3 m WHERE m.username = u.username) AS isManager, "
                + "(SELECT e.fullName FROM tblEmployee3 e WHERE e.username = u.username) AS empName, "
                + "(SELECT c.fullName FROM tblMember3 mb JOIN tblCustomer3 c ON c.id = mb.tblCustomer3id "
                + "  WHERE mb.username = u.username) AS memName "
                + "FROM tblUser3 u WHERE u.username = ? AND u.password = ? AND u.status = 'ACTIVE'";
        try (PreparedStatement ps = conn().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, MemberDAO3.sha256(password));
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                User3 u;
                if (rs.getInt("isManager") > 0) {
                    Manager3 m = new Manager3();
                    m.setFullName(rs.getString("empName"));
                    u = m;
                } else if (rs.getString("memName") != null) {
                    Member3 m = new Member3();
                    model.Customer3 c = new model.Customer3();
                    c.setFullName(rs.getString("memName"));
                    m.setCustomer(c);
                    u = m;
                } else {
                    return null; // nhan vien ban ve: ngoai pham vi
                }
                u.setUsername(rs.getString("username"));
                u.setStatus(rs.getString("status"));
                return u;
            }
        } catch (Exception e) {
            throw new IllegalStateException("Loi truy van CSDL: " + e.getMessage(), e);
        }
    }
}
