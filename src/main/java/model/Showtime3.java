package model;

import java.sql.Date;
import java.sql.Time;

/** Suất chiếu của một phim tại một phòng chiếu. */
public class Showtime3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Date showDate;
    private Time startTime;
    private double basePrice;
    private String status;
    private Movie3 movie;
    private Auditorium3 auditorium;
    private Time endTime;

    public Showtime3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getShowDate() { return showDate; }
    public void setShowDate(Date showDate) { this.showDate = showDate; }
    public Time getStartTime() { return startTime; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Movie3 getMovie() { return movie; }
    public void setMovie(Movie3 movie) { this.movie = movie; }
    public Auditorium3 getAuditorium() { return auditorium; }
    public void setAuditorium(Auditorium3 auditorium) { this.auditorium = auditorium; }
    public Time getEndTime() { return endTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }
}
