package rs.isa.mrs.trio.iceipice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.RestaurantType;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantDTO;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantTypeRepository;

/**
 * Created by nikolalukic on 4/17/16.
 */
@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantTypeRepository restaurantTypeRepository;

    public Restaurant addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        updateRestaurant(restaurant, restaurantDTO);

        try{
            restaurant = restaurantRepository.save(restaurant);
            return restaurant;
        } catch (Exception e) {
            return null;
        }
    }

    public Restaurant editRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantDTO.getId());
        updateRestaurant(restaurant, restaurantDTO);

        try{
            restaurant = restaurantRepository.save(restaurant);
            return restaurant;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateRestaurant(Restaurant restaurant, RestaurantDTO restaurantDTO) {
        restaurant.setName(restaurantDTO.getName());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setPib(restaurantDTO.getPib());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setEmail(restaurantDTO.getEmail());
        if (restaurantDTO.getRestaurantTypeNames() != null) {
            for (String typeName : restaurantDTO.getRestaurantTypeNames()) {
                final RestaurantType restaurantType = restaurantTypeRepository.findByName(typeName);
                restaurant.getRestaurantTypes().add(restaurantType);
                restaurantType.getRestaurants().add(restaurant);
                restaurantTypeRepository.save(restaurantType);
            }
        }
    }

}
