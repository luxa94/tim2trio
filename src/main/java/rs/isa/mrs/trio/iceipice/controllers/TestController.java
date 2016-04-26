package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.*;
import rs.isa.mrs.trio.iceipice.repository.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by nikolalukic on 4/17/16.
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    SystemManagerRepository systemManagerRepository;

    @Autowired
    RestaurantManagerRepository restaurantManagerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    BartenderRepository bartenderRepository;

    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public void fillBase() {
        try {
            systemManagerRepository.deleteAll();
            restaurantManagerRepository.deleteAll();
            bartenderRepository.deleteAll();
            restaurantRepository.deleteAll();
            guestRepository.deleteAll();
            Restaurant r = new Restaurant("r","r","r","r","r","r");
            SystemManager sm = new SystemManager("aaa", "aaa", "aaa", "aaa", "asdasd", new Date());
            RestaurantManager rm = new RestaurantManager("rm","rm","rm","rm","123", new Date(),r);
            Bartender br = new Bartender("br","br","Petar","Perić","123", new Date(),"42", "L");
            Guest g = new Guest("gg", "gg", "gg", "gg", "123", new Date());
            bartenderRepository.save(br);
            restaurantRepository.save(r);
            systemManagerRepository.save(sm);
            guestRepository.save(g);
            restaurantManagerRepository.save(rm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
