package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Auditorium3;
import model.Movie3;
import model.Showtime3;
import model.ShowtimeStat3;

/** DAO thong ke cac suat chieu cua mot phim (doanh thu, so ve, ti le lap day). */
public class ShowtimeStatDAO3 extends DAO3 {

    /**
     * @param movieId  ma phim da chon
     * @param branchId ma chi nhanh; 0 = toan he thong
     * @param from     ngay bat dau
     * @param to       ngay ket thuc
     * @return thong ke tung suat chieu cua phim trong khoang ngay, xep theo ngay/gio chieu
     */
    public List<ShowtimeStat3> getRevenueByShowtime(int movieId, int branchId, Date from, Date to) {
        List<ShowtimeStat3> list = new ArrayList<>();
        String sql = "SELECT s.id, s.showDate, s.startTime, s.basePrice, a.id AS audId, a.name AS audName, "
                + "(SELECT COUNT(*) FROM tblSeat3 se WHERE se.tblAuditorium3id = a.id) AS capacity, "
                + "COUNT(t.id) AS ticketCount, COALESCE(SUM(t.actualPrice),0) AS revenue "
                + "FROM tblShowtime3 s "
                + "JOIN tblAuditorium3 a ON a.id = s.tblAuditorium3id "
                + "LEFT JOIN tblTicket3 t ON t.tblShowtime3id = s.id AND EXISTS "
                + "   (SELECT 1 FROM tblInvoice3 i WHERE i.id = t.tblInvoice3id AND i.status = 'PAID') "
                + "WHERE s.tblMovie3id = ? AND s.showDate BETWEEN ? AND ? AND (? = 0 OR a.tblBranch3id = ?) "
                + "GROUP BY s.id, s.showDate, s.startTime, s.basePrice, a.id, a.name "
                + "ORDER BY s.showDate, s.startTime";
        try (PreparedStatement ps = conn().prepareStatement(sql)) {
            ps.setInt(1, movieId);
            ps.setDate(2, from);
            ps.setDate(3, to);
            ps.setInt(4, branchId);
            ps.setInt(5, branchId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Auditorium3 a = new Auditorium3();
                    a.setId(rs.getInt("audId"));
                    a.setName(rs.getString("audName"));
                    a.setCapacity(rs.getInt("capacity"));
                    Movie3 m = new Movie3();
                    m.setId(movieId);
                    Showtime3 s = new Showtime3();
                    s.setId(rs.getInt("id"));
                    s.setShowDate(rs.getDate("showDate"));
                    s.setStartTime(rs.getTime("startTime"));
                    s.setBasePrice(rs.getDouble("basePrice"));
                    s.setAuditorium(a);
                    s.setMovie(m);
                    ShowtimeStat3 st = new ShowtimeStat3();
                    st.setShowtime(s);
                    st.setTicketCount(rs.getInt("ticketCount"));
                    st.setRevenue(rs.getDouble("revenue"));
                    st.setOccupancyRate(a.getCapacity() == 0 ? 0 : 100.0 * st.getTicketCount() / a.getCapacity());
                    list.add(st);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Loi truy van CSDL: " + e.getMessage(), e);
        }
        return list;
    }
}
