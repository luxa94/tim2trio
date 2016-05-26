package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Reservation;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.repository.ReservationReporsitory;

/**
 * Created by Nina on 26-May-16.
 */
@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    ReservationReporsitory reservationReporsitory;

    @RequestMapping(value = "/reservations/all", method = RequestMethod.GET)
    public ResponseEntity getAllReservations() {
        return new ResponseEntity<>(reservationReporsitory.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneReservation(@PathVariable long id) {
        final Reservation reservation = reservationReporsitory.findById(id);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/reservation/update", method = RequestMethod.POST)
    public ResponseEntity updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {

        final Restaurant restaurant = restaurantService.editRestaurant(restaurantDTO);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

}
