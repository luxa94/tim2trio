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

    @JsonBackReference("cook-article-type")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cooks")
    private Set<ArticleType> articleTypes;

    @JsonBackReference("cook-order-item")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cooks")
    private Set<OrderItem> orderItems;

    @JsonBackReference("cook-shift")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cooks")
    private Set<Shift> shifts;

    public Restaurant getRestaurant(){
        if (shifts.isEmpty()){
            return null;
        }
        else{
            return shifts.iterator().next().getRestaurant();
        }
    }

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

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public Cook(String email, String password, String name, String surname, String phoneNumber, Date birthDate, String type, String dressSize, String footwearSize) {
        super(email, password, name, surname, phoneNumber, birthDate,type);
        this.dressSize = dressSize;
        this.footwearSize = footwearSize;


    }

}
