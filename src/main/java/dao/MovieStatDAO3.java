package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Movie3;
import model.MovieStat3;

/**
 * DAO thong ke phim theo doanh thu. Doanh thu = tong gia ve (tblTicket3.actualPrice) cua cac ve
 * thuoc hoa don da thanh toan (PAID), cho cac suat chieu co ngay chieu trong khoang [from, to].
 * Cac thuoc tinh thong ke la thuoc tinh dan xuat nen khong co bang rieng.
 */
public class MovieStatDAO3 extends DAO3 {

    /**
     * @param branchId ma chi nhanh; 0 = toan he thong
     * @param from     ngay bat dau (ngay chieu)
     * @param to       ngay ket thuc (ngay chieu)
     * @return danh sach thong ke tung phim, xep doanh thu giam dan
     */
    public List<MovieStat3> getRevenueByMovie(int branchId, Date from, Date to) {
        List<MovieStat3> list = new ArrayList<>();
        String sql = "SELECT m.id, m.title, m.genre, COUNT(t.id) AS ticketCount, SUM(t.actualPrice) AS revenue "
                + "FROM tblTicket3 t "
                + "JOIN tblInvoice3 i ON i.id = t.tblInvoice3id AND i.status = 'PAID' "
                + "JOIN tblShowtime3 s ON s.id = t.tblShowtime3id "
                + "JOIN tblAuditorium3 a ON a.id = s.tblAuditorium3id "
                + "JOIN tblMovie3 m ON m.id = s.tblMovie3id "
                + "WHERE s.showDate BETWEEN ? AND ? AND (? = 0 OR a.tblBranch3id = ?) "
                + "GROUP BY m.id, m.title, m.genre ORDER BY revenue DESC";
        try (PreparedStatement ps = conn().prepareStatement(sql)) {
            ps.setDate(1, from);
            ps.setDate(2, to);
            ps.setInt(3, branchId);
            ps.setInt(4, branchId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Movie3 m = new Movie3();
                    m.setId(rs.getInt("id"));
                    m.setTitle(rs.getString("title"));
                    m.setGenre(rs.getString("genre"));
                    MovieStat3 st = new MovieStat3();
                    st.setMovie(m);
                    st.setTicketCount(rs.getInt("ticketCount"));
                    st.setRevenue(rs.getDouble("revenue"));
                    list.add(st);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Loi truy van CSDL: " + e.getMessage(), e);
        }
        return list;
    }
}
