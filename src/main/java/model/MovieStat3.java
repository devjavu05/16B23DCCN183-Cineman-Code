package model;

/** Lớp thống kê: doanh thu của một phim (thuộc tính dẫn xuất, không có bảng). */
public class MovieStat3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Movie3 movie;
    private int ticketCount;
    private double revenue;

    public MovieStat3() {}

    public Movie3 getMovie() { return movie; }
    public void setMovie(Movie3 movie) { this.movie = movie; }
    public int getTicketCount() { return ticketCount; }
    public void setTicketCount(int ticketCount) { this.ticketCount = ticketCount; }
    public double getRevenue() { return revenue; }
    public void setRevenue(double revenue) { this.revenue = revenue; }
}
