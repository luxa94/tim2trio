package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.BaseUser;
import rs.isa.mrs.trio.iceipice.model.Reservation;

/**
 * Created by Nina on 26-May-16.
 */
public interface ReservationReporsitory extends JpaRepository<Reservation, Long> {

    Reservation findById(long id);

}
