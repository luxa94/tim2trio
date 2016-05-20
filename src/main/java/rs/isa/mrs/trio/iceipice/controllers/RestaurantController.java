package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantDTO;
import rs.isa.mrs.trio.iceipice.repository.RestaurantManagerRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantTypeRepository;
import rs.isa.mrs.trio.iceipice.services.RestaurantService;

/**
 * Created by nikolalukic on 4/17/16.
 */
@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantManagerRepository restaurantManagerRepository;

    @Autowired
    RestaurantTypeRepository restaurantTypeRepository;

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants/all", method = RequestMethod.GET)
    public ResponseEntity getAllRestaurants() {
        return new ResponseEntity<>(restaurantRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/restaurant/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneRestaurant(@PathVariable long id) {
        final Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            restaurant.getMenus();
            restaurant.getAreas();
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/restaurant/oneM/{idman}", method = RequestMethod.GET)
    public ResponseEntity getOneRestaurantFromManager(@PathVariable long idman) {
        long id = restaurantManagerRepository.findById(idman).getRestaurant().getId();
        final Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            restaurant.getMenus();
            restaurant.getAreas();
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/restaurant/create", method = RequestMethod.POST)
    public ResponseEntity addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {

        final Restaurant restaurant = restaurantService.addRestaurant(restaurantDTO);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/restaurant/update", method = RequestMethod.POST)
    public ResponseEntity updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {

        final Restaurant restaurant = restaurantService.editRestaurant(restaurantDTO);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
