package dto;

import java.util.Date;

public class ReportFavoriteUser {
    private String id;
    private String email;
    private String fullname;
    private Date shareDate;

    // Constructor PHẢI khớp JPQL
    public ReportFavoriteUser(String id, String email, String fullname, Date shareDate) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.shareDate = shareDate;
    }

    // Getter + Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }
}