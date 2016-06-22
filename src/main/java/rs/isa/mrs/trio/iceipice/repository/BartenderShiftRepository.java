package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.BartenderShift;

import java.util.List;

/**
 * Created by Sandra on 18.6.2016.
 */
public interface BartenderShiftRepository extends JpaRepository<BartenderShift, Long> {

    BartenderShift findById(long id);

    List<BartenderShift> findByBartender_Id(long id);

}
