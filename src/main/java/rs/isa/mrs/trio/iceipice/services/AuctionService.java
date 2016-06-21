package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Auction;
import rs.isa.mrs.trio.iceipice.model.dto.AuctionDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;

/**
 * Created by Sandra on 20.6.2016.
 */
@Service
public class AuctionService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    AuctionRepository auctionRepository;

    public Auction addAuction(AuctionDTO auctionDTO){
        Auction a = new Auction();
        a.setFrom_date(auctionDTO.getFrom_date());
        a.setTo_date(auctionDTO.getTo_date());
        a.setStatus(auctionDTO.getStatus());
        a.setRestaurant(restaurantRepository.findById(auctionDTO.getRestaurantId()));

        try {
            auctionRepository.save(a);
            return a;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }
}
