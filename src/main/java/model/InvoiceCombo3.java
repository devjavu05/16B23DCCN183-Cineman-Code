package model;

/** Dòng combo trong hóa đơn. */
public class InvoiceCombo3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int quantity;
    private Combo3 combo;

    public InvoiceCombo3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Combo3 getCombo() { return combo; }
    public void setCombo(Combo3 combo) { this.combo = combo; }
}
