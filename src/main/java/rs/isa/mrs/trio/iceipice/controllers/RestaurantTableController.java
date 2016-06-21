package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.RestaurantTable;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantTableDTO;
import rs.isa.mrs.trio.iceipice.services.RestaurantTableService;

import java.util.Arrays;

/**
 * Created by nikolalukic on 5/22/16.
 */
@RestController
@RequestMapping("/api")
public class RestaurantTableController {

    @Autowired
    RestaurantTableService restaurantTableService;

    @RequestMapping("/tables/restaurant/{id}")
    public ResponseEntity getTablesForRestaurant(@PathVariable long id) {
        return new ResponseEntity<>(restaurantTableService.findByRestaurantId(id), HttpStatus.OK);
    }

    @RequestMapping("/tables/restaurantManager/{id}")
    public ResponseEntity getTablesForRestaurantManager(@PathVariable long id) {
        return new ResponseEntity<>(restaurantTableService.findByManagerId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/tables/new", method = RequestMethod.POST)
    public ResponseEntity addTable(@RequestBody RestaurantTableDTO restaurantTableDTO) {

        final RestaurantTable restaurantTable = restaurantTableService.createTable(restaurantTableDTO);
        if (restaurantTable != null) {
            return new ResponseEntity<>(restaurantTable, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/tables/update/all/{managerId}", method = RequestMethod.POST)
    public ResponseEntity updateAll(@PathVariable long managerId, @RequestBody RestaurantTableDTO[] tables) {
        restaurantTableService.updateAll(Arrays.asList(tables));
        return new ResponseEntity<>(restaurantTableService.findByManagerId(managerId), HttpStatus.OK);
    }

    @RequestMapping(value = "/tables/waiter/{id}", method = RequestMethod.GET)
    public ResponseEntity getTablesForWaiter(@PathVariable long id) {
        return new ResponseEntity<>(restaurantTableService.getForWaiter(id), HttpStatus.OK);
    }
}
