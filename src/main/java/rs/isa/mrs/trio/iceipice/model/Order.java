package rs.isa.mrs.trio.iceipice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "order_pls")
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_tables",
            joinColumns = {@JoinColumn(name = "order_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "table_id", nullable = false)})
    private Set<RestaurantTable> restaurantTables;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Set<RestaurantTable> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(Set<RestaurantTable> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }

}
