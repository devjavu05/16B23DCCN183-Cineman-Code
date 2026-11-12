package model;

import java.sql.Date;

/** Phim. */
public class Movie3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String originalTitle;
    private String genre;
    private int duration;
    private String ageRating;
    private Date releaseDate;
    private String status;
    private String director;
    private String mainCast;
    private String subtitleLanguage;
    private String description;
    private String posterUrl;
    private String trailerUrl;

    public Movie3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getOriginalTitle() { return originalTitle; }
    public void setOriginalTitle(String originalTitle) { this.originalTitle = originalTitle; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getAgeRating() { return ageRating; }
    public void setAgeRating(String ageRating) { this.ageRating = ageRating; }
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getMainCast() { return mainCast; }
    public void setMainCast(String mainCast) { this.mainCast = mainCast; }
    public String getSubtitleLanguage() { return subtitleLanguage; }
    public void setSubtitleLanguage(String subtitleLanguage) { this.subtitleLanguage = subtitleLanguage; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }
    public String getTrailerUrl() { return trailerUrl; }
    public void setTrailerUrl(String trailerUrl) { this.trailerUrl = trailerUrl; }
}
