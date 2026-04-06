package dto;

import java.util.Date;

public class ReportShareFriend {

    private String fullname;
    private String email;
    private String emails; // danh sách email gửi đến
    private Date favoriteDate;
    private Date shareDate;

    // 🔥 Constructor QUAN TRỌNG (phải đúng thứ tự JPQL)
    public ReportShareFriend(String fullname, String email, String emails, Date favoriteDate, Date shareDate) {
        this.fullname = fullname;
        this.email = email;
        this.emails = emails;
        this.favoriteDate = favoriteDate;
        this.shareDate = shareDate;
    }

    // Getter & Setter

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Date getFavoriteDate() {
        return favoriteDate;
    }

    public void setFavoriteDate(Date favoriteDate) {
        this.favoriteDate = favoriteDate;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }
}