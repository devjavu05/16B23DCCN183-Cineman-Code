package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Customer3;
import model.Member3;

/** DAO cho thanh vien: luu tai khoan + khach hang + thanh vien trong mot giao dich. */
public class MemberDAO3 extends DAO3 {

    /**
     * Luu thanh vien moi vao tblUser3, tblCustomer3, tblMember3 (mot transaction).
     * Sau khi luu, id sinh tu dong duoc gan lai vao member.getCustomer().
     * @return true neu luu thanh cong
     */
    public boolean register(Member3 m) {
        Connection c = null;
        try {
            c = conn();
            c.setAutoCommit(false);
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO tblUser3(username,password,status) VALUES (?,?,'ACTIVE')")) {
                ps.setString(1, m.getUsername());
                ps.setString(2, sha256(m.getPassword()));
                ps.executeUpdate();
            }
            Customer3 cu = m.getCustomer();
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO tblCustomer3(fullName,birthDate,gender,phone,email,address,createdDate) VALUES (?,?,?,?,?,?,CURDATE())",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cu.getFullName());
                ps.setDate(2, cu.getBirthDate());
                ps.setString(3, cu.getGender());
                ps.setString(4, cu.getPhone());
                ps.setString(5, cu.getEmail());
                ps.setString(6, cu.getAddress());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    cu.setId(rs.getInt(1));
                }
            }
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO tblMember3(username,tblCustomer3id,loyaltyPoints,registerDate) VALUES (?,?,?,CURDATE())")) {
                ps.setString(1, m.getUsername());
                ps.setInt(2, cu.getId());
                ps.setInt(3, m.getLoyaltyPoints());
                ps.executeUpdate();
            }
            c.commit();
            return true;
        } catch (Exception e) {
            try { if (c != null) c.rollback(); } catch (Exception ignore) { }
            return false;
        } finally {
            try { if (c != null) c.setAutoCommit(true); } catch (Exception ignore) { }
        }
    }

    /** Ma hoa mat khau SHA-256 (ban demo; he thong that nen dung bcrypt/argon2 co salt). */
    static String sha256(String s) throws Exception {
        byte[] d = MessageDigest.getInstance("SHA-256").digest(s.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : d) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
