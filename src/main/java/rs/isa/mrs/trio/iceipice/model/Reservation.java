package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "start_hour", nullable = false)
    private String start_hour;

    @Column(name = "end_hour", nullable = false)
    private String end_hour;


    @ManyToMany(fetch = FetchType.EAGER,   cascade = CascadeType.ALL, mappedBy="reservations")
    private List<Guest> guests;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reservation_table",
            joinColumns = {@JoinColumn(name = "reservation_id",  nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "table_id", nullable = false)})
    private Set<RestaurantTable> restaurant_tables;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Reservation(Date date, String start_hour, String end_hour, List<Guest> guests, Set<RestaurantTable> restaurant_tables, Restaurant restaurant) {
        this.date = date;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
        this.guests = guests;
        this.restaurant_tables = restaurant_tables;
        this.restaurant = restaurant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public Set<RestaurantTable> getRestaurant_tables() {
        return restaurant_tables;
    }

    public void setRestaurant_tables(Set<RestaurantTable> restaurant_tables) {
        this.restaurant_tables = restaurant_tables;
    }

    public Reservation() {
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
}
