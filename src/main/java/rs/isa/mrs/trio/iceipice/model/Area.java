package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by Nina on 10-Apr-16.
 */
@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
