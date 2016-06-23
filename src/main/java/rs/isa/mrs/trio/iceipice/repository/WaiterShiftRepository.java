package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.WaiterShift;

import java.util.List;

/**
 * Created by Sandra on 22.6.2016.
 */
public interface WaiterShiftRepository extends JpaRepository<WaiterShift, Long> {
    public WaiterShift findById(long id);

    List<WaiterShift> findByWaiter_Id(long id);
}
