package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.HashSet;
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
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Cook> cooks;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Bartender> bartenders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shift")
    private Set<WaiterShift> waiterShifts;

    public Shift(String shift_type, Boolean active, Restaurant restaurant) {
        this.shift_type = shift_type;
        this.active = active;
        this.restaurant = restaurant;
    }

    public Shift() {
        this.waiterShifts = new HashSet<>();
        this.bartenders = new HashSet<>();
        this.cooks = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShift_type() {
        return shift_type;
    }

    public void setShift_type(String shift_type) {
        this.shift_type = shift_type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Cook> getCooks() {
        return cooks;
    }

    public void setCooks(Set<Cook> cooks) {
        this.cooks = cooks;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<WaiterShift> getWaiterShifts() {
        return waiterShifts;
    }

    public void setWaiterShifts(Set<WaiterShift> waiterShifts) {
        this.waiterShifts = waiterShifts;
    }

    public Set<Bartender> getBartenders() {
        return bartenders;
    }

    public void setBartenders(Set<Bartender> bartenders) {
        this.bartenders = bartenders;
    }
}
