package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Customer3;
import model.Invoice3;
import model.InvoiceStat3;
import model.PaymentMethod3;

/** DAO thong ke cac hoa don ban ve cua mot suat chieu. */
public class InvoiceStatDAO3 extends DAO3 {

    /**
     * @param showtimeId ma suat chieu da chon
     * @return cac hoa don (da thanh toan) co ve cua suat chieu, kem so ve va tong tien ve cua suat do
     */
    public List<InvoiceStat3> getInvoicesByShowtime(int showtimeId) {
        List<InvoiceStat3> list = new ArrayList<>();
        String sql = "SELECT i.id, i.invoiceCode, i.createdAt, i.status, i.salesStaffUsername, "
                + "c.fullName, p.name AS payName, COUNT(t.id) AS ticketCount, SUM(t.actualPrice) AS totalAmount "
                + "FROM tblInvoice3 i "
                + "JOIN tblTicket3 t ON t.tblInvoice3id = i.id AND t.tblShowtime3id = ? "
                + "JOIN tblPaymentMethod3 p ON p.id = i.tblPaymentMethod3id "
                + "LEFT JOIN tblCustomer3 c ON c.id = i.tblCustomer3id "
                + "WHERE i.status = 'PAID' "
                + "GROUP BY i.id, i.invoiceCode, i.createdAt, i.status, i.salesStaffUsername, c.fullName, p.name "
                + "ORDER BY i.createdAt";
        try (PreparedStatement ps = conn().prepareStatement(sql)) {
            ps.setInt(1, showtimeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice3 inv = new Invoice3();
                    inv.setId(rs.getInt("id"));
                    inv.setInvoiceCode(rs.getString("invoiceCode"));
                    inv.setCreatedAt(rs.getTimestamp("createdAt"));
                    inv.setStatus(rs.getString("status"));
                    String name = rs.getString("fullName");
                    if (name != null) {
                        Customer3 c = new Customer3();
                        c.setFullName(name);
                        inv.setCustomer(c);
                    }
                    PaymentMethod3 pm = new PaymentMethod3();
                    pm.setName(rs.getString("payName"));
                    inv.setPaymentMethod(pm);
                    InvoiceStat3 st = new InvoiceStat3();
                    st.setInvoice(inv);
                    st.setTicketCount(rs.getInt("ticketCount"));
                    st.setTotalAmount(rs.getDouble("totalAmount"));
                    list.add(st);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Loi truy van CSDL: " + e.getMessage(), e);
        }
        return list;
    }
}
