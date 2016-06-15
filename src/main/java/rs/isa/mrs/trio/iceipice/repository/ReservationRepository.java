package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.BaseUser;
import rs.isa.mrs.trio.iceipice.model.Reservation;

import java.util.List;

/**
 * Created by Nina on 26-May-16.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findById(long id);
    List<Reservation> findByGuest_Id(long id);


}
