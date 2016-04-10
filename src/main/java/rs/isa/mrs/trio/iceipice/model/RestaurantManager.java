package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by Sandra on 10.4.2016.
 */
@Entity
@Table(name = "restaurant_manager")
public class RestaurantManager extends BaseUser{

    public RestaurantManager() {
        this.setType("restaurant_manager");
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
