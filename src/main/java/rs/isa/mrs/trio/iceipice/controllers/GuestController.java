package rs.isa.mrs.trio.iceipice.controllers;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Guest;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.dto.AddFriendDTO;
import rs.isa.mrs.trio.iceipice.model.dto.GuestDTO;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantDTO;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;
import rs.isa.mrs.trio.iceipice.services.GuestService;

import java.util.ArrayList;
import java.util.Iterator;

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

    @RequestMapping(value = "/guest/all", method = RequestMethod.GET)
    public ResponseEntity getAllGuests() {
        return new ResponseEntity<>(guestRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/guest/all/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllFriends(@PathVariable long id)  {
        final Guest guest =  guestRepository.findById(id);
        final java.util.List<Guest> allGuests = guestRepository.findAll();
        final java.util.List<GuestDTO> allDTOGuests = new ArrayList<>(allGuests.size());
        for(Guest guest1: allGuests){
            GuestDTO guestDTO = new GuestDTO(guest1);
            allDTOGuests.add(guestDTO);
            if(guest1.isFriendsWith(guest)){
                guestDTO.setFriend(true);
            }
        }
        return new ResponseEntity<>(allDTOGuests, HttpStatus.OK);
    }

    @RequestMapping(value = "/guest/one/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/guest/addFriend", method = RequestMethod.POST)
    public ResponseEntity addFriend(@RequestBody AddFriendDTO addFriendDTO) {
        if (addFriendDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (addFriendDTO.getMyId() == 0 || addFriendDTO.getFriendId() == 0 ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        final Guest guest = guestRepository.findById(addFriendDTO.getMyId());
        final Guest friend = guestRepository.findById(addFriendDTO.getFriendId());
        if(guest == null || friend == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(guest.isFriendsWith(friend)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        guest.getFriends().add(friend);
        friend.getFriends().add(guest);
        guestRepository.save(guest);
        guestRepository.save(friend);

        return new ResponseEntity<>(HttpStatus.OK);



    }

    @RequestMapping(value = "/guest/removeFriend", method = RequestMethod.POST)
    public ResponseEntity removeFriend(@RequestBody AddFriendDTO addFriendDTO) {
        if (addFriendDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (addFriendDTO.getMyId() == 0 || addFriendDTO.getFriendId() == 0 ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        final Guest guest = guestRepository.findById(addFriendDTO.getMyId());
        final Guest friend = guestRepository.findById(addFriendDTO.getFriendId());
        if(guest == null || friend == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!guest.isFriendsWith(friend)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Iterator<Guest> iterator = guest.getFriends().iterator();
        while (iterator.hasNext()) {
            Guest element = iterator.next();
            if (element.getId() == friend.getId()) {
                iterator.remove();
            }
        }
        iterator = friend.getFriends().iterator();
        while (iterator.hasNext()) {
            Guest element = iterator.next();
            if (element.getId() == guest.getId()) {
                iterator.remove();
            }
        }
        guestRepository.save(guest);
        guestRepository.save(friend);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
