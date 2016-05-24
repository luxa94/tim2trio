package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Menu;

/**
 * Created by Sandra on 23.5.2016.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findById(long id);
}
