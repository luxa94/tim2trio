package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Date;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Guest guest;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Grade> grades;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Invitation> invitations;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Restaurant_table> restaurant_tables;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Order> orders;

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

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public Set<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<Invitation> invitations) {
        this.invitations = invitations;
    }

    public Set<Restaurant_table> getRestaurant_tables() {
        return restaurant_tables;
    }

    public void setRestaurant_tables(Set<Restaurant_table> restaurant_tables) {
        this.restaurant_tables = restaurant_tables;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
