package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Area;

import java.util.List;

/**
 * Created by nikolalukic on 5/22/16.
 */
public interface AreaRepository extends JpaRepository<Area, Long> {

    Area findByName(String name);

    Area findById(long id);

    List<Area> findByRestaurant_Id(long id);

}
