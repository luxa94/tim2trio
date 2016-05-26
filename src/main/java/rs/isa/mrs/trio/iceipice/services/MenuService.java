package rs.isa.mrs.trio.iceipice.services;

import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import rs.isa.mrs.trio.iceipice.model.Menu;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.dto.MenuDTO;
import rs.isa.mrs.trio.iceipice.repository.MenuRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;

/**
 * Created by Sandra on 25.5.2016.
 */
@Serialization
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public Menu createFromDTO(MenuDTO menuDTO){
        Menu menu = new Menu();
        final Restaurant r = restaurantRepository.findById(menuDTO.getRestaurantId());
        if (r != null){
            menu.setRestaurant(r);
            menuRepository.save(menu);
            return menu;
        }
        return null;
    }

}
