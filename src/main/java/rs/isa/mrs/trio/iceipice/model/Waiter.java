package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "waiter")
public class Waiter extends BaseUser {

    public Waiter() {
        this.setType("waiter");
    }

    @Column(name = "dress_size", nullable = false)
    private String dressSize;

    @Column(name = "footwearSize", nullable = false)
    private String footwearSize;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<WaiterShift> waiterShifts;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Order> orders;

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
}
