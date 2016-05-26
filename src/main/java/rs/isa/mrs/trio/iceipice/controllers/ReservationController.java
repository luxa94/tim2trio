package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Reservation;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationDTO;
import rs.isa.mrs.trio.iceipice.repository.ReservationRepository;
import rs.isa.mrs.trio.iceipice.services.ReservationService;

/**
 * Created by Nina on 26-May-16.
 */
@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = "/reservations/all", method = RequestMethod.GET)
    public ResponseEntity getAllReservations() {
        return new ResponseEntity<>(reservationRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneReservation(@PathVariable long id) {
        final Reservation reservation = reservationRepository.findById(id);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/reservation/update", method = RequestMethod.POST)
    public ResponseEntity updateReservation(@RequestBody ReservationDTO reservationDTO) {

        final Reservation reservation = reservationService.editReservation(reservationDTO);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/reservation/create", method = RequestMethod.POST)
    public ResponseEntity createReservation(@RequestBody ReservationDTO reservationDTO) {

        final Reservation reservation = reservationService.createReservation(reservationDTO);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

}
