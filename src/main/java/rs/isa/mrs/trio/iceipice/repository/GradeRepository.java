package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Grade;

import java.util.List;

/**
 * Created by Nina on 19-Jun-16.
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {

    Grade findById(long id);

    List<Grade> findByReservation_id(long id);



}
