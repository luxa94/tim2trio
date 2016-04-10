package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "pib", nullable = false, unique = true)
    private String pib;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Restaurant_type> restaurant_types;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<RestaurantManager> restaurantManagers;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Area> areas;

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

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Restaurant_type> getRestaurant_types() {
        return restaurant_types;
    }

    public void setRestaurant_types(Set<Restaurant_type> restaurant_types) {
        this.restaurant_types = restaurant_types;
    }

    public Set<RestaurantManager> getRestaurantManagers() {
        return restaurantManagers;
    }

    public void setRestaurantManagers(Set<RestaurantManager> restaurantManagers) {
        this.restaurantManagers = restaurantManagers;
    }

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }
}
