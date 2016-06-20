package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.AuctionItemType;

/**
 * Created by Sandra on 20.6.2016.
 */
public interface AuctionItemTypeRepository  extends JpaRepository<AuctionItemType, Long> {
    AuctionItemType findById(long id);
}
