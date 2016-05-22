package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Area;
import rs.isa.mrs.trio.iceipice.model.dto.AreaDTO;
import rs.isa.mrs.trio.iceipice.services.AreaService;
import rs.isa.mrs.trio.iceipice.services.RestaurantManagerService;

/**
 * Created by nikolalukic on 5/22/16.
 */
@RestController
@RequestMapping("/api")
public class AreaController {

    @Autowired
    RestaurantManagerService restaurantManagerService;

    @Autowired
    AreaService areaService;

    @RequestMapping(value = "/areas/forManager/{id}", method = RequestMethod.GET)
    public ResponseEntity getAreasForRestaurantManager(@PathVariable long id) {
        return new ResponseEntity<>(restaurantManagerService.findById(id).getRestaurant().getAreas(), HttpStatus.OK);
    }

    @RequestMapping(value = "/areas/new", method = RequestMethod.POST)
    public ResponseEntity addArea(@RequestBody AreaDTO areaDTO) {
        Area area = areaService.createArea(areaDTO);

        if (area != null) {
            return new ResponseEntity<>(area, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
