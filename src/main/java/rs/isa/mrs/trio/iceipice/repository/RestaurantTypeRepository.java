package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.RestaurantType;

/**
 * Created by nikolalukic on 4/17/16.
 */
public interface RestaurantTypeRepository extends JpaRepository<RestaurantType, Long> {

    RestaurantType findByName(String name);

}
