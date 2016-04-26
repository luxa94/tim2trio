package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.RestaurantManager;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantManagerDTO;
import rs.isa.mrs.trio.iceipice.repository.RestaurantManagerRepository;
import rs.isa.mrs.trio.iceipice.services.RestaurantManagerService;

/**
 * Created by nikolalukic on 4/22/16.
 */
@RestController
@RequestMapping("/api")
public class RestaurantManagerController {

    @Autowired
    RestaurantManagerService restaurantManagerService;

    @Autowired
    RestaurantManagerRepository restaurantManagerRepository;

    @RequestMapping(value = "/restaurantManager/create", method = RequestMethod.POST)
    public ResponseEntity createRestaurantManager(@RequestBody RestaurantManagerDTO restaurantManagerDTO) {
        RestaurantManager restaurantManager = restaurantManagerService.createFromDTO(restaurantManagerDTO);
        if (restaurantManager != null) {
            restaurantManager.getRestaurant();
            return new ResponseEntity<>(restaurantManager, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/restaurantManager/update", method = RequestMethod.POST)
    public ResponseEntity updateRestaurantManager(@RequestBody RestaurantManagerDTO restaurantManagerDTO){
        final RestaurantManager rm = restaurantManagerService.editRestaurantManager(restaurantManagerDTO);
        if(rm != null){
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/restaurantManager/one/{id}")
    public ResponseEntity getOne(@PathVariable long id) {
        final RestaurantManager rm = restaurantManagerRepository.findById(id);
        if (rm != null) {
            return new ResponseEntity<>(rm, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
