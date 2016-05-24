package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private Set<MenuItem> menuItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Menu( Restaurant restaurant) {
        this.menuItems = new HashSet<MenuItem>();
        this.restaurant = restaurant;
    }
}
