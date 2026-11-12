package model;

/** Lớp thống kê: doanh thu và tỉ lệ lấp đầy của một suất chiếu. */
public class ShowtimeStat3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Showtime3 showtime;
    private int ticketCount;
    private double revenue;
    private double occupancyRate;

    public ShowtimeStat3() {}

    public Showtime3 getShowtime() { return showtime; }
    public void setShowtime(Showtime3 showtime) { this.showtime = showtime; }
    public int getTicketCount() { return ticketCount; }
    public void setTicketCount(int ticketCount) { this.ticketCount = ticketCount; }
    public double getRevenue() { return revenue; }
    public void setRevenue(double revenue) { this.revenue = revenue; }
    public double getOccupancyRate() { return occupancyRate; }
    public void setOccupancyRate(double occupancyRate) { this.occupancyRate = occupancyRate; }
}
