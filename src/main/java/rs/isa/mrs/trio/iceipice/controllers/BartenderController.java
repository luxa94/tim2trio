package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Bartender;
import rs.isa.mrs.trio.iceipice.model.BartenderShift;
import rs.isa.mrs.trio.iceipice.model.dto.BartenderDTO;
import rs.isa.mrs.trio.iceipice.model.dto.BartenderShiftDTO;
import rs.isa.mrs.trio.iceipice.repository.BartenderRepository;
import rs.isa.mrs.trio.iceipice.repository.BartenderShiftRepository;
import rs.isa.mrs.trio.iceipice.services.BartenderService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nina on 10-May-16.
 */
@RestController
@RequestMapping("/api")
public class BartenderController {

    @Autowired
    BartenderRepository bartenderRepository;

    @Autowired
    BartenderService bartenderService;

    @Autowired
    BartenderShiftRepository bartenderShiftRepository;

    @RequestMapping(value = "/bartender/all", method = RequestMethod.GET)
    public ResponseEntity getAllBartenders() {
        return new ResponseEntity<>(bartenderRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bartender/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneBartender(@PathVariable long id) {
        final Bartender bartender = bartenderRepository.findById(id);
        if (bartender != null) {

            return new ResponseEntity<>(bartender, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/bartender/update", method = RequestMethod.POST)
    public ResponseEntity updateBartender(@RequestBody BartenderDTO bartenderDTO) {

        final Bartender bartender = bartenderService.editBartender(bartenderDTO);
        if (bartender != null) {
            return new ResponseEntity<>(bartender, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/bartender/allFromR/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllBartendersFromRestaurant(@PathVariable long id){
        Set<Bartender> bartenders = new HashSet<Bartender>();
        for (Bartender b : bartenderRepository.findAll()){
            if (b.getRestaurant().getId() == id)
                bartenders.add(b);
        }
        return new ResponseEntity<>(bartenders, HttpStatus.OK);
    }


    @RequestMapping(value = "/bartenderShift/newShift", method = RequestMethod.POST)
    public ResponseEntity addShifts(@RequestBody BartenderShiftDTO bartenderShiftDTO) {
        bartenderService.createBartenderShift(bartenderShiftDTO);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/bartenderShift/getAllShiftsFromRestaurant/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllShifts(@PathVariable long id) {
        List<BartenderShift> retval = new ArrayList<BartenderShift>();
        for (BartenderShift bs : bartenderShiftRepository.findAll()){
            if (bs.getBartender().getRestaurant().getId() == id)
                retval.add(bs);
        }
        return new ResponseEntity<>(retval, HttpStatus.OK);
    }

    @RequestMapping(value = "/bartenderShifts/forOne/{id}", method = RequestMethod.GET)
    public ResponseEntity getShiftsForBartender(@PathVariable long id) {
        return new ResponseEntity<>(bartenderShiftRepository.findByBartender_Id(id), HttpStatus.OK);
    }
}
