package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Waiter;

/**
 * Created by nikolalukic on 4/10/16.
 */
public interface WaiterRepository extends JpaRepository<Waiter, Long> {

    Waiter findByEmail(String email);

    Waiter findByEmailAndPassword(String email, String password);

}
