package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.Bartender;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.RestaurantManager;
import rs.isa.mrs.trio.iceipice.model.SystemManager;
import rs.isa.mrs.trio.iceipice.repository.BartenderRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantManagerRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;
import rs.isa.mrs.trio.iceipice.repository.SystemManagerRepository;

import java.util.Date;

/**
 * Created by nikolalukic on 4/17/16.
 */
@RestController
@RequestMapping("/api")
public class TestController {

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
            Restaurant r = new Restaurant("r","r","r","r","r","r");
            SystemManager sm = new SystemManager("aaa", "aaa", "aaa", "aaa", "asdasd", new Date());
            RestaurantManager rm = new RestaurantManager("rm","rm","rm","rm","123", new Date(),r);
            Bartender br = new Bartender("br","br","Petar","Perić","0646406444", new Date(),"42", "L");
            bartenderRepository.save(br);
            restaurantRepository.save(r);
            systemManagerRepository.save(sm);
            restaurantManagerRepository.save(rm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
