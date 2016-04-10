package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.BaseUser;

/**
 * Created by nikolalukic on 4/10/16.
 */
public interface BaseUserRepository extends JpaRepository<BaseUser, Long>{

    BaseUser findByEmail(String email);

    BaseUser findByEmailAndPassword(String email, String password);

}
