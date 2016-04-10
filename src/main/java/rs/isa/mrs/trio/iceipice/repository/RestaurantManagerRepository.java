package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.RestaurantManager;

/**
 * Created by nikolalukic on 4/10/16.
 */
public interface RestaurantManagerRepository extends JpaRepository<RestaurantManager, Long> {

    RestaurantManager findByEmail(String email);

    RestaurantManager findByEmailAndPassword(String email, String password);

}
