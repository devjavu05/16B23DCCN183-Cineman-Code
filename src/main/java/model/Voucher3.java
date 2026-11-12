package model;

import java.sql.Date;

/** Mã giảm giá áp dụng cho hóa đơn. */
public class Voucher3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String voucherCode;
    private String discountType;
    private double discountValue;
    private String condition;
    private Date startDate;
    private Date endDate;
    private int maxUses;
    private int usedCount;

    public Voucher3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    public double getDiscountValue() { return discountValue; }
    public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public int getMaxUses() { return maxUses; }
    public void setMaxUses(int maxUses) { this.maxUses = maxUses; }
    public int getUsedCount() { return usedCount; }
    public void setUsedCount(int usedCount) { this.usedCount = usedCount; }
}
