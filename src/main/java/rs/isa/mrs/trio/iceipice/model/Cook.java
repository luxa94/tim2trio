package rs.isa.mrs.trio.iceipice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "cook")
public class Cook extends BaseUser {

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    public Cook() {
        this.setType(UserTypes.COOK);
    }

    public Cook(String email, String password, String name, String surname, String phoneNumber, Date birthDate, String dressSize, String footwearSize) {
        super(email, password, name, surname, phoneNumber, birthDate, UserTypes.COOK);
        this.dressSize = dressSize;
        this.footwearSize = footwearSize;
    }

    @Column(name = "dress_size", nullable = false)
    private String dressSize;

    @Column(name = "footwearSize", nullable = false)
    private String footwearSize;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ArticleType> articleTypes;
    
    public String getDressSize() {
        return dressSize;
    }

    public void setDressSize(String dressSize) {
        this.dressSize = dressSize;
    }

    public String getFootwearSize() {
        return footwearSize;
    }

    public void setFootwearSize(String footwearSize) {
        this.footwearSize = footwearSize;
    }

    public Set<ArticleType> getArticleTypes() {
        return articleTypes;
    }

    public void setArticleTypes(Set<ArticleType> articleTypes) {
        this.articleTypes = articleTypes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
