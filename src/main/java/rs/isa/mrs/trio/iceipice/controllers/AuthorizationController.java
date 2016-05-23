package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.BaseUser;
import rs.isa.mrs.trio.iceipice.model.Guest;
import rs.isa.mrs.trio.iceipice.model.dto.LoginDTO;
import rs.isa.mrs.trio.iceipice.repository.BaseUserRepository;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;
import rs.isa.mrs.trio.iceipice.services.EmailService;
import rs.isa.mrs.trio.iceipice.services.GuestService;
import rs.isa.mrs.trio.iceipice.services.InviteFriendService;

/**
 * Created by nikolalukic on 4/10/16.
 */
@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @Autowired
    EmailService emailService;

    @Autowired
    BaseUserRepository baseUserRepository;

    @Autowired
    GuestService guestService;

    @Autowired
    GuestRepository guestRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        final String email = loginDTO.getEmail();
        final String password = loginDTO.getPassword();

        BaseUser baseUser = baseUserRepository.findByEmailAndPassword(email, password);
        if (baseUser != null) {
            return new ResponseEntity<>(baseUser, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/guest/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody Guest guest) {
        try {
            System.out.println("asdasdasdasdsd");
            guest.setConfirmed(false);
            guest = guestRepository.save(guest);
            emailService.sendMail(guest);
            return new ResponseEntity<>(guest, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/verify/{id}", method = RequestMethod.GET)
    public ResponseEntity verify(@PathVariable long id) {
        final Guest guest = guestService.verify(id);
        if (guest != null) {
            return new ResponseEntity<>(guest, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
