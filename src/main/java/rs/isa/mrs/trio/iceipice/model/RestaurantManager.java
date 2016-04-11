package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Sandra on 10.4.2016.
 */
@Entity
@Table(name = "restaurant_manager")
public class RestaurantManager extends BaseUser{
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
