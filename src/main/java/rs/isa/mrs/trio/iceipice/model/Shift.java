package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Date;
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

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    @Column(name = "day", nullable = false)
    private Date day;

    @Column(name = "valid", nullable = false)
    private boolean valid;

    @Column(name = "start_hour", nullable = false)
    private String startHour;

    @Column(name = "end_hour", nullable = false)
    private String endHour;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Shift(Restaurant restaurant, Date day, boolean valid, String startHour, String endHour) {
        this.restaurant = restaurant;
        this.day = day;
        this.valid = valid;
        this.startHour = startHour;
        this.endHour = endHour;
    }
}
