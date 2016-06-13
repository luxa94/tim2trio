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
    ShiftRepository shiftRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public void fillBase() {
        try {
            systemManagerRepository.deleteAll();
            restaurantManagerRepository.deleteAll();


            shiftRepository.deleteAll();
            menuItemRepository.deleteAll();
            menuRepository.deleteAll();
            articleRepository.deleteAll();
            articleTypeRepository.deleteAll();
            bartenderRepository.deleteAll();
            cookRepository.deleteAll();
            waiterRepository.deleteAll();
            reservationRepository.deleteAll();
            restaurantTableRepository.deleteAll();
            areaRepository.deleteAll();
            restaurantRepository.deleteAll();
            guestRepository.deleteAll();

            Restaurant r = new Restaurant("Travica","Najbolja jagnjetina","PIB123", "021 400 400","Novosadskog Sajma 5" ,"travica@gmail.com");
            Restaurant r1 = new Restaurant("Piknik","Opis","PIB2","021500 500","Ribarac 2","picnic@gmail.com");
            SystemManager sm = new SystemManager("aaa", "aaa", "aaa", "aaa", "asdasd", new Date());
            RestaurantManager rm = new RestaurantManager("rm","rm","rm","rm","123", new Date(),r);

            Bartender br = new Bartender("br","br","Petar","Perić","123", new Date(),"42", "L");
            Guest g = new Guest("gg", "gg", "Ana", "Anastasijevic", "123", new Date());
            Guest g1 = new Guest("g1", "g1", "Antonina", "Ninic", "123", new Date());
            Guest g2 = new Guest("g2", "g2", "Petar", "Peric", "123", new Date());
            Guest g3 = new Guest("g3", "g3", "Mitar", "Miric", "123", new Date());
            Guest g4 = new Guest("g4", "g4", "Blaza", "Blazic", "123", new Date());
            Cook ck = new Cook("cook", "cook", "cook", "cook", "123", new Date(), "M", "41");
            Waiter w = new Waiter("waiter", "waiter", "Marko", "Marković", "123456789", new Date(), "waiter", "XL", "46");

            Area area1 = new Area("nepusacki", r);
            RestaurantTable rt1 = new RestaurantTable("sto1", 4, area1, "{\"type\":\"rect\",\"left\":15,\"top\":426,\"width\":50,\"height\":50,\"fill\":\"white\"}");
            Set<RestaurantTable> tables = new HashSet<RestaurantTable>();
            tables.add(rt1);
            Reservation res1 = new Reservation(new Date(), "22:00","14:00", g, tables);
            br.setRestaurant(r);
            ck.setRestaurant(r);
            w.setRestaurant(r);

            ArticleType atype = new ArticleType("neki tip artikla");
            ArticleType atype2 = new ArticleType("neki tip artikla2222");
            Article a1 = new Article("Ime Artikla1", "Opis artikla 1", atype);
            Article a2 = new Article("Ime Artikla2", "Opis artikla 2", atype);
            Article a3 = new Article("Ime Artikla3", "Opis artikla 3", atype);
            Article a4 = new Article("Ime Artikla4", "Opis artikla 1", atype);
            Article a5 = new Article("Ime Artikla5", "Opis artikla 2", atype);
            Article a6 = new Article("Ime Artikla6", "Opis artikla 3", atype2);
            Article a7 = new Article("Ime Artikla7", "Opis artikla 1", atype2);
            Article a8 = new Article("Ime Artikla8", "Opis artikla 2", atype2);
            Article a9 = new Article("Ime Artikla9", "Opis artikla 3", atype2);
            Menu m1 = new Menu(r);
            MenuItem mi1 = new MenuItem(100, new Date(), new Date(), a1, m1);
            MenuItem mi2 = new MenuItem(200, new Date(), new Date(), a2, m1);
            MenuItem mi3 = new MenuItem(300, new Date(), new Date(), a3, m1);
            MenuItem mi4 = new MenuItem(1000, new Date(), new Date(), a4, m1);
            MenuItem mi5 = new MenuItem(2000, new Date(), new Date(), a5, m1);
            MenuItem mi6 = new MenuItem(3000, new Date(), new Date(), a6, m1);
            MenuItem mi7 = new MenuItem(100, new Date(), new Date(), a7, m1);
            MenuItem mi8 = new MenuItem(200, new Date(), new Date(), a8, m1);
            MenuItem mi9 = new MenuItem(300, new Date(), new Date(), a9, m1);

            restaurantRepository.save(r);
            bartenderRepository.save(br);
            systemManagerRepository.save(sm);
            guestRepository.save(g);
            guestRepository.save(g1);
            guestRepository.save(g2);
            guestRepository.save(g3);
            guestRepository.save(g4);
            restaurantManagerRepository.save(rm);
            cookRepository.save(ck);
            waiterRepository.save(w);
            articleTypeRepository.save(atype);
            articleTypeRepository.save(atype2);
            articleRepository.save(a1);
            articleRepository.save(a2);
            articleRepository.save(a3);
            articleRepository.save(a4);
            articleRepository.save(a5);
            articleRepository.save(a6);
            articleRepository.save(a7);
            articleRepository.save(a8);
            articleRepository.save(a9);
            menuRepository.save(m1);
            menuItemRepository.save(mi1);
            menuItemRepository.save(mi2);
            menuItemRepository.save(mi3);
            menuItemRepository.save(mi4);
            menuItemRepository.save(mi5);
            menuItemRepository.save(mi6);
            menuItemRepository.save(mi7);
            menuItemRepository.save(mi8);
            menuItemRepository.save(mi9);
            areaRepository.save(area1);
            restaurantTableRepository.save(rt1);
            reservationRepository.save(res1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
