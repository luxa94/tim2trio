package rs.isa.mrs.trio.iceipice.model;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Order> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
