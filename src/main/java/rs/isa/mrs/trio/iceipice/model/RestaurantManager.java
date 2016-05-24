package rs.isa.mrs.trio.iceipice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sandra on 10.4.2016.
 */
@Entity
@Table(name = "restaurant_manager")
public class RestaurantManager extends BaseUser{

    public RestaurantManager() {
        this.setType(UserTypes.RESTAURANT_MANAGER);
    }

    public RestaurantManager(String email, String password, String name, String surname, String phoneNumber, Date birthDate, Restaurant restaurant) {
        super(email, password, name, surname, phoneNumber, birthDate, UserTypes.RESTAURANT_MANAGER);
        this.restaurant = restaurant;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
