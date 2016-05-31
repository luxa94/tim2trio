package rs.isa.mrs.trio.iceipice.model;

import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "bartender")
public class Bartender extends BaseUser {

    public Bartender() {
        this.setType(UserTypes.BARTENDER);
    }

    @Column(name = "dress_size", nullable = false)
    private String dressSize;

    @Column(name = "footwear_size", nullable = false)
    private String footwearSize;

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    public String getDressSize() {
        return dressSize;
    }

    public void setDressSize(String dressSize) {
        this.dressSize = dressSize;
    }

    public String getFootwearSize() {
        return footwearSize;
    }

    public void setFootwearSize(String footwearSize) {
        this.footwearSize = footwearSize;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Bartender(String email, String password, String name, String surname, String phoneNumber, Date birthDate, String footwearSize, String dressSize) {
        super(email, password, name, surname, phoneNumber, birthDate, UserTypes.BARTENDER);
        this.footwearSize = footwearSize;
        this.dressSize = dressSize;
    }


}
