package entity;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {

    private String id;
    private String password;
    private String email;
    private String fullname;
    private Boolean admin;
//    private List<Favorite> favorites;
//    private List<Share> shares;

    public Users() {
    }

    public Users(String id, String password) {
       this.id = id;
       this.password = password;
    }

    public Users(String id, String password, String email, String fullname) {
       this.id = id;
       this.password = password;
       this.email = email;
       this.fullname = fullname;
    }

    public Users(String id, String password, String email, String fullname, Boolean admin) {
       this.id = id;
       this.password = password;
       this.email = email;
       this.fullname = fullname;
       this.admin = admin;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public String getId() {
       return this.id;
    }

    public void setId(String id) {
       this.id = id;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
       return this.password;
    }

    public void setPassword(String password) {
       this.password = password;
    }

    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
       return this.email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    @Column(name = "fullname", nullable = false)
    public String getFullname() {
       return this.fullname;
    }

    public void setFullname(String fullname) {
       this.fullname = fullname;
    }

    @Column(name = "admin")
    public Boolean getAdmin() {
       return this.admin;
    }

    public void setAdmin(Boolean admin) {
       this.admin = admin;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    public List<Favorite> getFavorites() {
//       return this.favorites;
//    }
//
//    public void setFavorites(List<Favorite> favorites) {
//       this.favorites = favorites;
//    }
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    public List<Share> getShares() {
//       return this.shares;
//    }
//
//    public void setShares(List<Share> shares) {
//       this.shares = shares;
//    }

    @Override
    public String toString() {
       return "User{" + "id='" + id + '\'' + ", password='" + password + '\'' + ", email='" + email + '\''
             + ", fullname='" + fullname + '\'' + ", admin=" + admin + ", favoritesCount="
//             + (favorites != null ? favorites.size() : 0) + ", sharesCount=" + (shares != null ? shares.size() : 0)
             + '}';
    }

}

