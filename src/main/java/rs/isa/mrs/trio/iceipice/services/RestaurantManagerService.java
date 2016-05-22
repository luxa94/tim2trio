package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.RestaurantManager;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantManagerDTO;
import rs.isa.mrs.trio.iceipice.repository.RestaurantManagerRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;

/**
 * Created by nikolalukic on 4/22/16.
 */
@Service
public class RestaurantManagerService {

    @Autowired
    RestaurantManagerRepository restaurantManagerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    BaseUserService baseUserService;

    public RestaurantManager createFromDTO(RestaurantManagerDTO restaurantManagerDTO) {
        RestaurantManager restaurantManager = new RestaurantManager();

        restaurantManager = loadFromDTO(restaurantManager, restaurantManagerDTO);

        return restaurantManager;
    }

    private RestaurantManager loadFromDTO(RestaurantManager restaurantManager, RestaurantManagerDTO restaurantManagerDTO) {
        try {
            baseUserService.extractData(restaurantManager, restaurantManagerDTO);

            final Restaurant restaurant = restaurantRepository.findById(restaurantManagerDTO.getRestaurantId());

            if (restaurant != null) {
                restaurant.getRestaurantManagers().add(restaurantManager);
                restaurantManager.setRestaurant(restaurant);
                final RestaurantManager rm = restaurantManagerRepository.save(restaurantManager);
                restaurantRepository.save(restaurant);
                return rm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RestaurantManager editRestaurantManager(RestaurantManagerDTO rmDTO) {
        RestaurantManager oldRM = restaurantManagerRepository.findByEmail(rmDTO.getEmail());
        updateRestaurantManager(oldRM, rmDTO);

        try {
            oldRM = restaurantManagerRepository.save(oldRM);
            return oldRM;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateRestaurantManager(RestaurantManager rm, RestaurantManagerDTO restaurantManagerDTO) {
        baseUserService.updateChangeable(rm, restaurantManagerDTO);
    }

    public RestaurantManager findById(long id) {
        return restaurantManagerRepository.findById(id);
    }

}
