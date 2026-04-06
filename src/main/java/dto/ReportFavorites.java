package dto;

import java.util.Date;

public class ReportFavorites {
    private String title;
    private Long likes;
    private Date newestDate;
    private Date oldestDate;

    // Constructor PHẢI khớp JPQL
    public ReportFavorites(String title, Long likes, Date newestDate, Date oldestDate) {
        this.title = title;
        this.likes = likes;
        this.newestDate = newestDate;
        this.oldestDate = oldestDate;
    }

    // Getter + Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Date getNewestDate() {
        return newestDate;
    }

    public void setNewestDate(Date newestDate) {
        this.newestDate = newestDate;
    }

    public Date getOldestDate() {
        return oldestDate;
    }

    public void setOldestDate(Date oldestDate) {
        this.oldestDate = oldestDate;
    }
}