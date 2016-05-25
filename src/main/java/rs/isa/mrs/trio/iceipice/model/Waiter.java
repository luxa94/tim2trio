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
@Table(name = "waiter")
public class Waiter extends BaseUser {

    public Waiter() {
        this.setType(UserTypes.WAITER);
    }

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    @Column(name = "dress_size", nullable = false)
    private String dressSize;

    @Column(name = "footwearSize", nullable = false)
    private String footwearSize;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "waiter")
    private Set<WaiterShift> waiterShifts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "waiter_orders",
            inverseJoinColumns = {@JoinColumn(name = "order_id", nullable = false)},
            joinColumns = {@JoinColumn(name = "waiter_id", nullable = false)})
    private Set<Order> orders;

    public Restaurant getRestaurant(){
        if (waiterShifts.isEmpty()){
            return null;
        }
        else{
            return waiterShifts.iterator().next().getShift().getRestaurant();
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

    public Set<WaiterShift> getWaiterShifts() {
        return waiterShifts;
    }

    public void setWaiterShifts(Set<WaiterShift> waiterShifts) {
        this.waiterShifts = waiterShifts;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Waiter(String email, String password, String name, String surname, String phoneNumber, Date birthDate, String type, String dressSize, String footwearSize) {
        super(email, password, name, surname, phoneNumber, birthDate, type);
        this.dressSize = dressSize;
        this.footwearSize = footwearSize;
    }
}
