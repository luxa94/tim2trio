package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.*;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    private Boolean graded;

    private long restaurantId;

    private RestaurantTableDTO restaurantTable;

    private List<GuestDTO> guests = new ArrayList<>();

    private List<OrderItemDTO> orders = new ArrayList<>();

    public ReservationDTO(Reservation reservation) {
        id = reservation.getId();
        date = reservation.getDate();
        start_hour = reservation.getStart_hour();
        end_hour = reservation.getEnd_hour();
        List<RestaurantTableDTO> tableDTO = new ArrayList<>();
        Iterator<RestaurantTable> tableIterator = reservation.getRestaurant_tables().iterator();
            while(tableIterator.hasNext()) {
            RestaurantTable table = tableIterator.next();
            tableDTO.add(new RestaurantTableDTO(table));
        }
        this.restaurant = new RestaurantDTO(reservation.getRestaurant());
        this.restaurantId = this.restaurant.getId();
        guests = new ArrayList<GuestDTO>();
        for(Guest guest: reservation.getGuests()) {
            GuestDTO dto = new GuestDTO(guest);
            guests.add(dto);
        }
        orders = new ArrayList<OrderItemDTO>();
       for(OrderItem order : reservation.getOrders()){
           OrderItemDTO dto = new OrderItemDTO(order);
          orders.add(dto);
       }
    }

    public ReservationDTO() {

    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", date=" + date +
                ", start_hour='" + start_hour + '\'' +
                ", end_hour='" + end_hour + '\'' +
                ", restaurant=" + restaurant +
                ", graded=" + graded +
                ", restaurantId=" + restaurantId +
                ", restaurantTable=" + restaurantTable +
                ", guests=" + guests +
                ", orders=" + orders +
                '}';
    }

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

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Boolean getGraded() {
        return graded;
    }

    public void setGraded(Boolean graded) {
        this.graded = graded;
    }

    public List<OrderItemDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemDTO> orders) {
        this.orders = orders;
    }
}
