package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import model.MembershipTier3;

/** DAO cho hang the thanh vien. */
public class MembershipTierDAO3 extends DAO3 {

    /** Lay hang khoi diem (hang co pointThreshold nho nhat). */
    public MembershipTier3 getBaseTier() {
        String sql = "SELECT id, tierName, description, pointThreshold FROM tblMembershipTier3 ORDER BY pointThreshold ASC LIMIT 1";
        try (Statement st = conn().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (!rs.next()) return null;
            MembershipTier3 t = new MembershipTier3();
            t.setId(rs.getInt("id"));
            t.setTierName(rs.getString("tierName"));
            t.setDescription(rs.getString("description"));
            t.setPointThreshold(rs.getInt("pointThreshold"));
            return t;
        } catch (Exception e) {
            throw new IllegalStateException("Loi truy van CSDL: " + e.getMessage(), e);
        }
    }
}
