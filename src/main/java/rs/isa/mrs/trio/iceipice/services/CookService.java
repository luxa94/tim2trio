package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Cook;
import rs.isa.mrs.trio.iceipice.model.dto.CookDTO;
import rs.isa.mrs.trio.iceipice.repository.CookRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;

/**
 * Created by Nina on 22-Apr-16.
 */
@Service
public class CookService {

    @Autowired
    CookRepository cookRepository;

    @Autowired
    BaseUserService baseUserService;

    @Autowired
    RestaurantRepository restaurantRepository;

    public Cook editCook(CookDTO cookDTO) {
        Cook cook = cookRepository.findById(cookDTO.getId());
        updateCook(cook, cookDTO);

        try {
            cook = cookRepository.save(cook);
            return cook;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateCook(Cook cook, CookDTO cookDTO) {
        baseUserService.updateChangeable(cook, cookDTO);
        baseUserService.extractData(cook,cookDTO);
        cook.setDressSize(cookDTO.getDressSize());
        cook.setFootwearSize(cookDTO.getFootwearSize());
        cook.setRestaurant(restaurantRepository.findById(cookDTO.getRestaurantId()));
    }

    public Cook addCook(CookDTO cookDTO) {
        Cook cook = new Cook();
        updateCook(cook, cookDTO);

        try {
            cook = cookRepository.save(cook);
            return cook;
        } catch (Exception e) {
            return null;
        }
    }

}
