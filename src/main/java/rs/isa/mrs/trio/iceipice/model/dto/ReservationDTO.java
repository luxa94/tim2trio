package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.Guest;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.RestaurantTable;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nina on 26-May-16.
 */
public class ReservationDTO {

    private long id;

    private Date date;

    private String start_hour;

    private String end_hour;

    private RestaurantDTO restaurant;

    private RestaurantTableDTO restaurantTable;

    private List<GuestDTO> guests = new ArrayList<>();

    // Treba da se dodaju stavke menija u porudzbinu!!!!!!!!!!

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

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantTableDTO getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTableDTO restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public List<GuestDTO> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestDTO> guests) {
        this.guests = guests;
    }
}
