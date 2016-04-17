package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Restaurant;

/**
 * Created by nikolalukic on 4/17/16.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findById(long id);

}
