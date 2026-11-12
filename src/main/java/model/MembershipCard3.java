package model;

import java.sql.Date;

/** Thẻ thành viên (1 khách hàng có tối đa 1 thẻ). */
public class MembershipCard3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String cardNumber;
    private Date issueDate;
    private String cardStatus;
    private MembershipTier3 tier;

    public MembershipCard3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
    public String getCardStatus() { return cardStatus; }
    public void setCardStatus(String cardStatus) { this.cardStatus = cardStatus; }
    public MembershipTier3 getTier() { return tier; }
    public void setTier(MembershipTier3 tier) { this.tier = tier; }
}
