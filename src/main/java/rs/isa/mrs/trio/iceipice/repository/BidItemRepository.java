package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.BidItem;

/**
 * Created by Sandra on 21.6.2016.
 */
public interface BidItemRepository extends JpaRepository<BidItem, Long> {
    public BidItem findById(long id);
}
