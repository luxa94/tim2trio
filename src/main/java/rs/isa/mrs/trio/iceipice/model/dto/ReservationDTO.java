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

    // Treba da se dodaju stavke menija u porudzbinu





}
