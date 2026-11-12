package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Branch3;

/** DAO cho chi nhanh rap. */
public class BranchDAO3 extends DAO3 {

    /** Lay danh sach tat ca chi nhanh de quan li chon khi xem thong ke. */
    public List<Branch3> getAllBranches() {
        List<Branch3> list = new ArrayList<>();
        String sql = "SELECT id, name, address, phone, openTime, closeTime FROM tblBranch3 ORDER BY id";
        try (Statement st = conn().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Branch3 b = new Branch3();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setAddress(rs.getString("address"));
                b.setPhone(rs.getString("phone"));
                b.setOpenTime(rs.getTime("openTime"));
                b.setCloseTime(rs.getTime("closeTime"));
                list.add(b);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Loi truy van CSDL: " + e.getMessage(), e);
        }
        return list;
    }
}
