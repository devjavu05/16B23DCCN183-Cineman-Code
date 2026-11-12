package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import model.Customer3;
import model.MembershipCard3;
import model.MembershipTier3;

/** DAO cho the thanh vien. The duoc luu thanh cac cot card* cua tblCustomer3 (quan he 1-1 da gop bang). */
public class MembershipCardDAO3 extends DAO3 {

    /**
     * Cap the thanh vien moi (hang khoi diem) cho khach hang va ghi vao tblCustomer3.
     * @param c khach hang da duoc luu (da co id)
     * @return the vua cap, hoac null neu loi
     */
    public MembershipCard3 issueCard(Customer3 c) {
        try {
            MembershipTier3 tier = new MembershipTierDAO3().getBaseTier();
            MembershipCard3 card = new MembershipCard3();
            card.setId(c.getId());
            card.setCardNumber(String.format("TV%06d", c.getId()));
            card.setIssueDate(new Date(System.currentTimeMillis()));
            card.setCardStatus("ACTIVE");
            card.setTier(tier);
            String sql = "UPDATE tblCustomer3 SET cardNumber=?, cardIssueDate=?, cardStatus=?, tblMembershipTier3id=? WHERE id=?";
            try (PreparedStatement ps = conn().prepareStatement(sql)) {
                ps.setString(1, card.getCardNumber());
                ps.setDate(2, card.getIssueDate());
                ps.setString(3, card.getCardStatus());
                ps.setInt(4, tier.getId());
                ps.setInt(5, c.getId());
                if (ps.executeUpdate() != 1) return null;
            }
            c.setCard(card);
            return card;
        } catch (Exception e) {
            return null;
        }
    }
}
