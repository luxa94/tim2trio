package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.repository.RestaurantTypeRepository;

/**
 * Created by nikolalukic on 4/17/16.
 */
@RestController
@RequestMapping("/api/restaurantTypes")
public class RestaurantTypeController {

    @Autowired
    RestaurantTypeRepository restaurantTypeRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getRestaurantTypes() {
        return new ResponseEntity<>(restaurantTypeRepository.findAll(), HttpStatus.OK);
    }

}
