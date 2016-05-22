package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.*;
import rs.isa.mrs.trio.iceipice.repository.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    CookRepository cookRepository;

    @Autowired
    WaiterRepository waiterRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public void fillBase() {
        try {
            systemManagerRepository.deleteAll();
            restaurantManagerRepository.deleteAll();
            bartenderRepository.deleteAll();
            restaurantTableRepository.deleteAll();
            areaRepository.deleteAll();
            restaurantRepository.deleteAll();
            guestRepository.deleteAll();
            cookRepository.deleteAll();
            waiterRepository.deleteAll();
            Restaurant r = new Restaurant("Travica","Najbolja jagnjetina","PIB123", "021 400 400","Novosadskog Sajma 5" ,"travica@gmail.com");
            Restaurant r1 = new Restaurant("Piknik","Opis","PIB2","021500 500","Ribarac 2","picnic@gmail.com");
            SystemManager sm = new SystemManager("aaa", "aaa", "aaa", "aaa", "asdasd", new Date());
            RestaurantManager rm = new RestaurantManager("rm","rm","rm","rm","123", new Date(),r);
            Set<RestaurantManager> set = new HashSet<>();
            set.add(rm);
            r.setRestaurantManagers(set);
            Bartender br = new Bartender("br","br","Petar","Perić","123", new Date(),"42", "L");
            Guest g = new Guest("gg", "gg", "Ana", "Anastasijevic", "123", new Date());
            Guest g1 = new Guest("g1", "g1", "Antonina", "Ninic", "123", new Date());
            Guest g2 = new Guest("g2", "g2", "Petar", "Peric", "123", new Date());
            Guest g3 = new Guest("g3", "g3", "Mitar", "Miric", "123", new Date());
            Guest g4 = new Guest("g4", "g4", "Blaza", "Blazic", "123", new Date());
            Cook ck = new Cook("cook", "cook", "cook", "cook", "123", new Date(), "cook", "M", "41");
            Waiter w = new Waiter("waiter", "waiter", "Marko", "Marković", "123456789", new Date(), "waiter", "XL", "46");
            bartenderRepository.save(br);
            restaurantRepository.save(r);
            restaurantRepository.save(r1);
            systemManagerRepository.save(sm);
            guestRepository.save(g);
            guestRepository.save(g1);
            guestRepository.save(g2);
            guestRepository.save(g3);
            guestRepository.save(g4);
            restaurantManagerRepository.save(rm);
            cookRepository.save(ck);
            waiterRepository.save(w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
