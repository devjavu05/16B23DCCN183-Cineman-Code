package model;

import java.util.List;

import java.sql.Timestamp;

/** Hóa đơn bán vé. */
public class Invoice3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String invoiceCode;
    private Timestamp createdAt;
    private String status;
    private Customer3 customer;
    private PaymentMethod3 paymentMethod;
    private double subTotal;
    private double discountAmount;
    private double total;
    private SalesStaff3 salesStaff;
    private Voucher3 voucher;
    private List<Ticket3> tickets;
    private List<InvoiceCombo3> invoiceCombos;

    public Invoice3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getInvoiceCode() { return invoiceCode; }
    public void setInvoiceCode(String invoiceCode) { this.invoiceCode = invoiceCode; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Customer3 getCustomer() { return customer; }
    public void setCustomer(Customer3 customer) { this.customer = customer; }
    public PaymentMethod3 getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod3 paymentMethod) { this.paymentMethod = paymentMethod; }
    public double getSubTotal() { return subTotal; }
    public void setSubTotal(double subTotal) { this.subTotal = subTotal; }
    public double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public SalesStaff3 getSalesStaff() { return salesStaff; }
    public void setSalesStaff(SalesStaff3 salesStaff) { this.salesStaff = salesStaff; }
    public Voucher3 getVoucher() { return voucher; }
    public void setVoucher(Voucher3 voucher) { this.voucher = voucher; }
    public List<Ticket3> getTickets() { return tickets; }
    public void setTickets(List<Ticket3> tickets) { this.tickets = tickets; }
    public List<InvoiceCombo3> getInvoiceCombos() { return invoiceCombos; }
    public void setInvoiceCombos(List<InvoiceCombo3> invoiceCombos) { this.invoiceCombos = invoiceCombos; }
}
