package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Guest;

/**
 * Created by nikolalukic on 4/10/16.
 */
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findById(long id);

    Guest findByEmail(String email);

    Guest findByEmailAndPassword(String email, String password);

}
