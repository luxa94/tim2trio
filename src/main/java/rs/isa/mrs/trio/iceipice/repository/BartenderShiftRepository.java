package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.BartenderShift;
/**
 * Created by Sandra on 18.6.2016.
 */
public interface BartenderShiftRepository extends JpaRepository<BartenderShift, Long> {

    BartenderShift findById(long id);
}
