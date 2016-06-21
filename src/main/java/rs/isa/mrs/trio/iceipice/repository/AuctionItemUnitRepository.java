package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.AuctionItemUnit;

/**
 * Created by Sandra on 20.6.2016.
 */
public interface AuctionItemUnitRepository  extends JpaRepository<AuctionItemUnit, Long> {
    AuctionItemUnit findById(long id);
}
