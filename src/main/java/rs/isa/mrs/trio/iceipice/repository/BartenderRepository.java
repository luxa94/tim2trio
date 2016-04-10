package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Bartender;

/**
 * Created by nikolalukic on 4/10/16.
 */
public interface BartenderRepository extends JpaRepository<Bartender, Long> {

    Bartender findByEmail(String email);

    Bartender findByEmailAndPassword(String email, String password);

}
