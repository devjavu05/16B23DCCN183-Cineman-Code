package model;

/** Ghế trong phòng chiếu (thành phần của Auditorium3). */
public class Seat3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String position;
    private String seatType;

    public Seat3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getSeatType() { return seatType; }
    public void setSeatType(String seatType) { this.seatType = seatType; }
}
