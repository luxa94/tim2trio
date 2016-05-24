package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Shift;
import rs.isa.mrs.trio.iceipice.model.Waiter;
import rs.isa.mrs.trio.iceipice.model.dto.WaiterDTO;
import rs.isa.mrs.trio.iceipice.repository.WaiterRepository;
import rs.isa.mrs.trio.iceipice.services.WaiterService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nina on 10-May-16.
 */
@RestController
@RequestMapping("/api")
public class WaiterController {

    @Autowired
    WaiterRepository waiterRepository;

    @Autowired
    WaiterService waiterService;

    @RequestMapping(value = "/waiter/all", method = RequestMethod.GET)
    public ResponseEntity getAllWaiters() {
        return new ResponseEntity<>(waiterRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiter/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneWaiter(@PathVariable long id) {
        final Waiter waiter = waiterRepository.findById(id);
        if (waiter != null) {

            return new ResponseEntity<>(waiter, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/waiter/allFromR/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllWaitersFromRestaurant(@PathVariable long id){
        Set<Waiter> waiters = new HashSet<Waiter>();
        for (Waiter w : waiterRepository.findAll()){
            if (w.getRestaurant().getId() == id)
                waiters.add(w);
        }
        return new ResponseEntity<>(waiters, HttpStatus.OK);
    }

    @RequestMapping(value = "/waiter/update", method = RequestMethod.POST)
    public ResponseEntity updateWaiter(@RequestBody WaiterDTO waiterDTO) {

        final Waiter waiter = waiterService.editWaiter(waiterDTO);
        if (waiter != null) {
            return new ResponseEntity<>(waiter, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
