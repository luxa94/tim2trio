package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Area;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.RestaurantManager;
import rs.isa.mrs.trio.iceipice.model.dto.AreaDTO;
import rs.isa.mrs.trio.iceipice.repository.AreaRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantManagerRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;

import java.util.List;

/**
 * Created by nikolalukic on 5/22/16.
 */
@Service
public class AreaService {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    RestaurantManagerRepository restaurantManagerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public Area createArea(AreaDTO areaDTO) {
        final RestaurantManager manager = restaurantManagerRepository.findById(areaDTO.getManagerId());
        final Restaurant restaurant = manager.getRestaurant();
        Area area = new Area();
        area.setName(areaDTO.getName());
        area.setRestaurant(restaurant);
        area.setColor(areaDTO.getColor());

        area = areaRepository.save(area);
        return area;
    }

    public List<Area> findByRestaurant(Restaurant restaurant) {
        return areaRepository.findByRestaurant_Id(restaurant.getId());
    }

}
