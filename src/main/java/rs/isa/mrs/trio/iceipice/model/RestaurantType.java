package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "restaurant_type")
public class RestaurantType {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Restaurant> restaurants;

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
