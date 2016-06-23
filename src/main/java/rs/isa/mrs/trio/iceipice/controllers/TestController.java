package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.*;
import rs.isa.mrs.trio.iceipice.repository.*;
import util.DateUtil;

import java.util.*;

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

    @Autowired
    BartenderShiftRepository bartenderShiftRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    FriendshipRequestRepository friendshipRequestRepository;

    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public void fillBase() {
        try {
            systemManagerRepository.deleteAll();
            restaurantManagerRepository.deleteAll();


            bartenderShiftRepository.deleteAll();
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
            friendshipRequestRepository.deleteAll();
            guestRepository.deleteAll();
            providerRepository.deleteAll();


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

            Provider p = new Provider("p","p","Dobrilo","Dobric","021/1234-5678",new Date(),"provider");

            Area area1 = new Area("nepusacki", r);
            RestaurantTable rt1 = new RestaurantTable("sto1", 4, area1, "{\"type\":\"rect\",\"left\":15,\"top\":426,\"width\":50,\"height\":50,\"fill\":\"white\"}");
            Set<RestaurantTable> tables = new HashSet<RestaurantTable>();
            tables.add(rt1);

            List<Guest> guests = new ArrayList<Guest>();
            guests.add(g);

            Reservation res1 = new Reservation(new Date(), "22:40","14:00", guests, tables, r1);
          //  Reservation res2 = new Reservation(new Date(), "22:00","18:00", guests, tables, r1);

            br.setRestaurant(r);
            ck.setRestaurant(r);
            w.setRestaurant(r);

            ArticleType foodType = new ArticleType("hrana");
            ArticleType drinkType = new ArticleType("pice");
            Article a1 = new Article("Karadjordjeva", "Opis artikla 1", foodType);
            Article a2 = new Article("Becka", "Opis artikla 2", foodType);
            Article a3 = new Article("Salata", "Opis artikla 3", foodType);
            Article a4 = new Article("Kolac", "Opis artikla 1", foodType);
            Article a5 = new Article("Pomfrit", "Opis artikla 2", foodType);
            Article a6 = new Article("Kafa", "Opis artikla 3", drinkType);
            Article a7 = new Article("Caj", "Opis artikla 1", drinkType);
            Article a8 = new Article("Plava Laguna", "Opis artikla 2", drinkType);
            Article a9 = new Article("Voda", "Opis artikla 3", drinkType);
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

            Shift sh1 = new Shift(r,new Date(),true, "01:01", "02:02");
            Shift sh2 = new Shift(r, DateUtil.addDays(new Date(),1),true, "01:01", "02:02");
            BartenderShift btsh1 = new BartenderShift(br,sh1);
            //CookShift cksh1 = new CookShift(ck,sh2);

            restaurantRepository.save(r);
            restaurantRepository.save(r1);
            bartenderRepository.save(br);
            systemManagerRepository.save(sm);
            providerRepository.save(p);
            guestRepository.save(g);
            guestRepository.save(g1);
            guestRepository.save(g2);
            guestRepository.save(g3);
            guestRepository.save(g4);
            restaurantManagerRepository.save(rm);
            cookRepository.save(ck);
            waiterRepository.save(w);
            articleTypeRepository.save(foodType);
            articleTypeRepository.save(drinkType);
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
            shiftRepository.save(sh1);

            shiftRepository.save(sh2);
            bartenderShiftRepository.save(btsh1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
