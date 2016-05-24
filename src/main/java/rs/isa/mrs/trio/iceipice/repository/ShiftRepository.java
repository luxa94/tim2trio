package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Shift;

/**
 * Created by Sandra on 24.5.2016.
 */
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    Shift findById(long id);

}
