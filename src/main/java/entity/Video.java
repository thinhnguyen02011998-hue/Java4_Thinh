package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Videos")
public class Video {

    private String id;
    private String title;
    private String poster;
    private Integer views;
    private String description;
    private Boolean active;
    private String link;
//    private List<Favorite> favorites;
//    private List<Share> shares;

    public Video() {
    }

    public Video(String id, String title, String poster, String description) {
       this.id = id;
       this.title = title;
       this.poster = poster;
       this.description = description;
    }

    //List<Favorite> favorites, List<Share> shares
    public Video(String id, String title, String poster, Integer views, String description, Boolean active, String link) {
       this.id = id;
       this.title = title;
       this.poster = poster;
       this.views = views;
       this.description = description;
       this.active = active;
       this.link=link;
//       this.favorites = favorites;
//       this.shares = shares;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public String getId() {
       return this.id;
    }

    public void setId(String id) {
       this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
       return this.title;
    }

    public void setTitle(String title) {
       this.title = title;
    }

    @Column(name = "poster", nullable = false)
    public String getPoster() {
       return this.poster;
    }

    public void setPoster(String poster) {
       this.poster = poster;
    }

    @Column(name = "views")
    public Integer getViews() {
       return this.views;
    }

    public void setViews(Integer views) {
       this.views = views;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
       return this.description;
    }

    public void setDescription(String description) {
       this.description = description;
    }

    @Column(name = "active")
    public Boolean getActive() {
       return this.active;
    }

    public void setActive(Boolean active) {
       this.active = active;
    }


    @Column(name = "link", nullable = false)
    public String getLink() {
       return this.link;
    }

    public void setLink(String link) {
       this.link = link;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "video")
//    public List<Favorite> getFavorites() {
//       return this.favorites;
//    }

//    public void setFavorites(List<Favorite> favorites) {
//       this.favorites = favorites;
//    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "video")
//    public List<Share> getShares() {
//       return this.shares;
//    }

//    public void setShares(List<Share> shares) {
//       this.shares = shares;
//    }

}
