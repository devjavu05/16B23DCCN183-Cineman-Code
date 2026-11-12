package model;

/** Combo bắp nước. */
public class Combo3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String description;
    private double price;
    private String status;

    public Combo3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
