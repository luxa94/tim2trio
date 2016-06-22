package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.CookShift;

import java.util.List;

/**
 * Created by nikolalukic on 6/19/16.
 */
public interface CookShiftRepository extends JpaRepository<CookShift, Long> {


    List<CookShift> findByCook_Id(long id);
}
