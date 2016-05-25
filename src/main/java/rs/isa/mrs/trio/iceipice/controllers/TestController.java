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

    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public void fillBase() {
        try {
            systemManagerRepository.deleteAll();
            restaurantManagerRepository.deleteAll();
            restaurantTableRepository.deleteAll();
            areaRepository.deleteAll();
            guestRepository.deleteAll();
            shiftRepository.deleteAll();
            menuRepository.deleteAll();
            articleRepository.deleteAll();
            articleTypeRepository.deleteAll();
            bartenderRepository.deleteAll();
            restaurantRepository.deleteAll();
            cookRepository.deleteAll();
            waiterRepository.deleteAll();

            Restaurant r = new Restaurant("Travica","Najbolja jagnjetina","PIB123", "021 400 400","Novosadskog Sajma 5" ,"travica@gmail.com");
            Restaurant r1 = new Restaurant("Piknik","Opis","PIB2","021500 500","Ribarac 2","picnic@gmail.com");
            SystemManager sm = new SystemManager("aaa", "aaa", "aaa", "aaa", "asdasd", new Date());
            RestaurantManager rm = new RestaurantManager("rm","rm","rm","rm","123", new Date(),r);
<<<<<<< HEAD
            Set<RestaurantManager> set = new HashSet<>();
            set.add(rm);
            r.setRestaurantManagers(set);


            //dodavanje menija za jelo
            Menu menu = new Menu(r);
            ArticleType atype = new ArticleType("ime tipa 1");
            Article a1 = new Article("Jelo1", "Opis jela1", atype);
            MenuItem mi1 = new MenuItem(100,new Date(),new Date(),a1,menu);
            Article a2 = new Article("Jelo2", "Opis jela2", atype);
            MenuItem mi2 = new MenuItem(2200,new Date(),new Date(),a2,menu);
            menu.getMenuItems().add(mi1);
            menu.getMenuItems().add(mi2);

=======
>>>>>>> ad06b2a23035f74296e9fc7e6f96b49b83cf3f3c
            Bartender br = new Bartender("br","br","Petar","Perić","123", new Date(),"42", "L");
            Guest g = new Guest("gg", "gg", "Ana", "Anastasijevic", "123", new Date());
            Guest g1 = new Guest("g1", "g1", "Antonina", "Ninic", "123", new Date());
            Guest g2 = new Guest("g2", "g2", "Petar", "Peric", "123", new Date());
            Guest g3 = new Guest("g3", "g3", "Mitar", "Miric", "123", new Date());
            Guest g4 = new Guest("g4", "g4", "Blaza", "Blazic", "123", new Date());
            Cook ck = new Cook("cook", "cook", "cook", "cook", "123", new Date(), "M", "41");
            Waiter w = new Waiter("waiter", "waiter", "Marko", "Marković", "123456789", new Date(), "waiter", "XL", "46");
<<<<<<< HEAD

            Shift shift = new Shift("tip smene", true, r);
            Set<Cook> cookSet = new HashSet<Cook>();
            cookSet.add(ck);
            Set<Bartender> barSet = new HashSet<Bartender>();
            barSet.add(br);
            Set<WaiterShift> waiterShiftSet = new HashSet<WaiterShift>();
            waiterShiftSet.add(new WaiterShift(w,shift));
            shift.setCooks(cookSet);
            shift.setBartenders(barSet);
            shift.setWaiterShifts(waiterShiftSet);
            Set<Shift> shifts = new HashSet<>();
            shifts.add(shift);
            r.setShifts(shifts);

            bartenderRepository.save(br);
=======
            br.setRestaurant(r);
            ck.setRestaurant(r);
            w.setRestaurant(r);
>>>>>>> ad06b2a23035f74296e9fc7e6f96b49b83cf3f3c
            restaurantRepository.save(r);
            bartenderRepository.save(br);
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
            menuRepository.save(menu);
            shiftRepository.save(shift);
            articleTypeRepository.save(atype);
            articleRepository.save(a1);
            articleRepository.save(a2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
