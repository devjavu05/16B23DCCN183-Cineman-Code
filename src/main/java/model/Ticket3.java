package model;

/** Vé xem phim, thành phần của hóa đơn. */
public class Ticket3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private double actualPrice;
    private String qrCode;
    private String status;
    private Showtime3 showtime;
    private Seat3 seat;

    public Ticket3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getActualPrice() { return actualPrice; }
    public void setActualPrice(double actualPrice) { this.actualPrice = actualPrice; }
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Showtime3 getShowtime() { return showtime; }
    public void setShowtime(Showtime3 showtime) { this.showtime = showtime; }
    public Seat3 getSeat() { return seat; }
    public void setSeat(Seat3 seat) { this.seat = seat; }
}
