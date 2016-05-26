package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Cook;
import rs.isa.mrs.trio.iceipice.model.Shift;
import rs.isa.mrs.trio.iceipice.model.dto.CookDTO;
import rs.isa.mrs.trio.iceipice.repository.CookRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;
import rs.isa.mrs.trio.iceipice.services.CookService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nina on 22-Apr-16.
 */
@RestController
@RequestMapping("/api")
public class CookController {

    @Autowired
    CookRepository cookRepository;

    @Autowired
    CookService cookService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(value = "/cook/all", method = RequestMethod.GET)
    public ResponseEntity getAllCooks() {
        return new ResponseEntity<>(cookRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cook/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneCook(@PathVariable long id) {
        final Cook cook = cookRepository.findById(id);
        if (cook != null) {

            return new ResponseEntity<>(cook, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cook/update", method = RequestMethod.POST)
    public ResponseEntity updateCook(@RequestBody CookDTO cookDTO) {

        final Cook cook = cookService.editCook(cookDTO);
        if (cook != null) {
            return new ResponseEntity<>(cook, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/cook/create", method = RequestMethod.POST)
    public ResponseEntity addCook(@RequestBody CookDTO cookDTO) {

        final Cook cook = cookService.addCook(cookDTO);
        if (cook != null) {
            return new ResponseEntity<>(cook, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/cook/allFromR/{idR}", method = RequestMethod.GET)
    public ResponseEntity getAllCooksFromRestaurant(@PathVariable long idR){

        Set<Cook> cooks = new HashSet<Cook>();
        for (Cook c : cookRepository.findAll()){
            if (c.getRestaurant().getId() == idR)
                cooks.add(c);
        }
        return new ResponseEntity<>(cooks, HttpStatus.OK);
    }

}
