package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.MenuItem;

/**
 * Created by Sandra on 25.5.2016.
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    MenuItem findById(long id);
}
