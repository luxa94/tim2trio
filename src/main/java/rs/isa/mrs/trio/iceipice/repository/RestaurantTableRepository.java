package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Area;
import rs.isa.mrs.trio.iceipice.model.RestaurantTable;

import java.util.Collection;
import java.util.List;

/**
 * Created by nikolalukic on 5/22/16.
 */
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    List<RestaurantTable> findByArea_Restaurant_Id(long id);

    List<RestaurantTable> findByArea(Area area);

    RestaurantTable findById(long id);
}
