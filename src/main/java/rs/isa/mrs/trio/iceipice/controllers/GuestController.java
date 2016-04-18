package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Guest;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.dto.GuestDTO;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantDTO;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;
import rs.isa.mrs.trio.iceipice.services.GuestService;

/**
 * Created by Nina on 17-Apr-16.
 */
@RestController
@RequestMapping("/api")
public class GuestController {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    GuestService guestService;

    @RequestMapping(value = "/guests/all", method = RequestMethod.GET)
    public ResponseEntity getAllGuests() {
        return new ResponseEntity<>(guestRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/guests/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneGuest(@PathVariable long id) {
        final Guest guest = guestRepository.findById(id);
        if (guest != null) {

            // ovde cu dodavati za fatch.lazy sta mi bude trebalo od gosta
            return new ResponseEntity<>(guest, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/guest/update", method = RequestMethod.POST)
    public ResponseEntity updateGuest(@RequestBody GuestDTO guestDTO) {

        final Guest guest = guestService.editGuest(guestDTO);
        if (guest != null) {
            return new ResponseEntity<>(guest, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
