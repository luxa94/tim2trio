package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.SystemManager;

/**
 * Created by nikolalukic on 4/10/16.
 */
public interface SystemManagerRepository extends JpaRepository<SystemManager, Long> {

    SystemManager findById(long id);

    SystemManager findByEmail(String email);

    SystemManager findByEmailAndPassword(String email, String password);

}
