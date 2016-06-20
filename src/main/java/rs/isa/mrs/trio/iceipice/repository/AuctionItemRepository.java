package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.AuctionItem;

/**
 * Created by Sandra on 19.6.2016.
 */
public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    AuctionItem findById(long id);
}
