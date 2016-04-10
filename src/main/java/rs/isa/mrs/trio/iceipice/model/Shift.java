package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nina on 10-Apr-16.
 */
@Entity
@Table(name = "shift")
public class Shift {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "shift_type", nullable = false)
    private String shift_type;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<WaiterShift> waiterShifts;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getShift_type() {
        return shift_type;
    }

    public void setShift_type(String shift_type) {
        this.shift_type = shift_type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<WaiterShift> getWaiterShifts() {
        return waiterShifts;
    }

    public void setWaiterShifts(Set<WaiterShift> waiterShifts) {
        this.waiterShifts = waiterShifts;
    }
}
