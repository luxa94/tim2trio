package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Cook;

/**
 * Created by nikolalukic on 4/10/16.
 */
public interface CookRepository extends JpaRepository<Cook, Long> {

    Cook findByEmail(String email);

    Cook findByEmailAndPassword(String email, String password);

}
