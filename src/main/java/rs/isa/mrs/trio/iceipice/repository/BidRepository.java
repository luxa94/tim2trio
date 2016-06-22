package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Bid;

/**
 * Created by Sandra on 21.6.2016.
 */
public interface BidRepository extends JpaRepository<Bid, Long> {
    public Bid findById(long id);
}
