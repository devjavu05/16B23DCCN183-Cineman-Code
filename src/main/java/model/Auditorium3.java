package model;

import java.util.List;

/** Phòng chiếu; capacity là thuộc tính dẫn xuất (đếm số ghế trong tblSeat3). */
public class Auditorium3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String screenType;
    private int capacity;
    private String seatMap;
    private List<Seat3> seats;

    public Auditorium3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getScreenType() { return screenType; }
    public void setScreenType(String screenType) { this.screenType = screenType; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public String getSeatMap() { return seatMap; }
    public void setSeatMap(String seatMap) { this.seatMap = seatMap; }
    public List<Seat3> getSeats() { return seats; }
    public void setSeats(List<Seat3> seats) { this.seats = seats; }
}
