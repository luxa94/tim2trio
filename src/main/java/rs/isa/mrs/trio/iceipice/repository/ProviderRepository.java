package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Provider;

/**
 * Created by nikolalukic on 4/10/16.
 */
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findByEmail(String email);

    Provider findByEmailAndPassword(String email, String password);

    Provider findById(long id);
}
