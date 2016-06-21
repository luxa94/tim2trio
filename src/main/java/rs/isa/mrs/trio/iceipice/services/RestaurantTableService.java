package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.RestaurantManager;
import rs.isa.mrs.trio.iceipice.model.RestaurantTable;
import rs.isa.mrs.trio.iceipice.model.Waiter;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantTableDTO;
import rs.isa.mrs.trio.iceipice.repository.AreaRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantManagerRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantTableRepository;
import rs.isa.mrs.trio.iceipice.repository.WaiterRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolalukic on 5/22/16.
 */
@Service
public class RestaurantTableService {

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    @Autowired
    RestaurantManagerRepository restaurantManagerRepository;

    @Autowired
    WaiterRepository waiterRepository;

    @Autowired
    AreaRepository areaRepository;

    public List<RestaurantTable> findByRestaurantId(long id) {
        return restaurantTableRepository.findByArea_Restaurant_Id(id);
    }

    public List<RestaurantTable> findByManagerId(long id) {
        final RestaurantManager manager = restaurantManagerRepository.findById(id);
        return restaurantTableRepository.findByArea_Restaurant_Id(manager.getRestaurant().getId());
    }

    public RestaurantTable createTable(RestaurantTableDTO restaurantTableDTO) {
        final RestaurantTable restaurantTable = new RestaurantTable();

        restaurantTable.setId(restaurantTableDTO.getId());
        restaurantTable.setName(restaurantTableDTO.getName());
        restaurantTable.setArea(areaRepository.findById(restaurantTableDTO.getArea()));
        restaurantTable.setCapacity(restaurantTableDTO.getCapacity());
        restaurantTable.setFabricTable(restaurantTableDTO.getFabricTable());

        return restaurantTableRepository.save(restaurantTable);
    }

    public void updateAll(List<RestaurantTableDTO> tablesDTO) {
        final List<RestaurantTable> tables = new ArrayList<>();
        for (RestaurantTableDTO dto : tablesDTO) {
            tables.add(createTable(dto));
        }
        restaurantTableRepository.save(tables);
    }

    public List<RestaurantTable> getForWaiter(long id) {
        final Waiter waiter = waiterRepository.findById(id);
        // TODO: change when shifts come to play
        return restaurantTableRepository.findByArea_Restaurant_Id(waiter.getRestaurant().getId());
    }

}
