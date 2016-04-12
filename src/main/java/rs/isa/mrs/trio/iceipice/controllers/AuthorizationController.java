package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.globals.UserTypes;
import rs.isa.mrs.trio.iceipice.model.BaseUser;
import rs.isa.mrs.trio.iceipice.model.Guest;
import rs.isa.mrs.trio.iceipice.model.dto.LoginDTO;
import rs.isa.mrs.trio.iceipice.repository.*;

/**
 * Created by nikolalukic on 4/10/16.
 */
@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @Autowired
    BartenderRepository bartenderRepository;

    @Autowired
    BaseUserRepository baseUserRepository;

    @Autowired
    CookRepository cookRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    RestaurantManagerRepository restaurantManagerRepository;

    @Autowired
    SystemManagerRepository systemManagerRepository;

    @Autowired
    WaiterRepository waiterRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        final String email = loginDTO.getEmail();
        final String password = loginDTO.getPassword();
        System.out.println(email + " " + password);

        BaseUser baseUser = baseUserRepository.findByEmailAndPassword(email, password);
        if (baseUser != null) {
//            if (baseUser.getType().equals(UserTypes.BARTENDER)) {
//                baseUser = bartenderRepository.findByEmailAndPassword(email, password);
//            } else if (baseUser.getType().equals(UserTypes.COOK)) {
//                baseUser = cookRepository.findByEmailAndPassword(email, password);
//            } else if (baseUser.getType().equals(UserTypes.GUEST)) {
//                baseUser = guestRepository.findByEmailAndPassword(email, password);
//            } else if (baseUser.getType().equals(UserTypes.PROVIDER)) {
//                baseUser = providerRepository.findByEmailAndPassword(email, password);
//            } else if (baseUser.getType().equals(UserTypes.RESTAURANT_MANAGER)) {
//                baseUser = restaurantManagerRepository.findByEmailAndPassword(email, password);
//            } else if (baseUser.getType().equals(UserTypes.SYSTEM_MANAGER)) {
//                baseUser = systemManagerRepository.findByEmailAndPassword(email, password);
//            } else if (baseUser.getType().equals(UserTypes.WAITER)) {
//                baseUser = waiterRepository.findByEmailAndPassword(email, password);
//            } else {
//                System.out.println(String.format("Unknown user type: %s", baseUser.getType()));
//                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
            return new ResponseEntity<>(baseUser, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/guest/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody Guest guest) {
        try {
            guest.setConfirmed(false);
            guest = guestRepository.save(guest);
            return new ResponseEntity<>(guest, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
