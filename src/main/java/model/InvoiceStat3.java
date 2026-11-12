package model;

/** Lớp thống kê: một hóa đơn trong một suất chiếu. */
public class InvoiceStat3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Invoice3 invoice;
    private int ticketCount;
    private double totalAmount;

    public InvoiceStat3() {}

    public Invoice3 getInvoice() { return invoice; }
    public void setInvoice(Invoice3 invoice) { this.invoice = invoice; }
    public int getTicketCount() { return ticketCount; }
    public void setTicketCount(int ticketCount) { this.ticketCount = ticketCount; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
