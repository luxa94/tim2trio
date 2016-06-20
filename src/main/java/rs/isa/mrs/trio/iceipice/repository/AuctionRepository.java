package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Auction;

/**
 * Created by Sandra on 19.6.2016.
 */
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Auction findById(long id);
}
