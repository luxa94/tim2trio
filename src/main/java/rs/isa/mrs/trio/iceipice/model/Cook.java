package rs.isa.mrs.trio.iceipice.model;

import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.*;
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

    @Column(name = "dress_size", nullable = false)
    private String dressSize;

    @Column(name = "footwearSize", nullable = false)
    private String footwearSize;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ArticleType> articleTypes;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Shift> shifts;

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
}
