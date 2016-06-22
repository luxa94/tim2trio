package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Auction;
import rs.isa.mrs.trio.iceipice.model.Bid;
import rs.isa.mrs.trio.iceipice.model.BidItem;
import rs.isa.mrs.trio.iceipice.model.Provider;
import rs.isa.mrs.trio.iceipice.model.dto.BidDTO;
import rs.isa.mrs.trio.iceipice.model.dto.BidItemDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionRepository;
import rs.isa.mrs.trio.iceipice.repository.BidItemRepository;
import rs.isa.mrs.trio.iceipice.repository.BidRepository;
import rs.isa.mrs.trio.iceipice.repository.ProviderRepository;

import java.util.Date;

/**
 * Created by Sandra on 21.6.2016.
 */
@Service
public class BidService {

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    AuctionRepository auctionRepository;

    public Bid updateBid(long bidId,BidDTO bidDTO){
        //UPOZORENJE! mogu se update-ovati samo oni atributi koje je moguce menjati u formi!
        Bid b = bidRepository.findById(bidId);
        b.setTimestamp(new Date());
        b.setCurrency(bidDTO.getCurrency());
        b.setPrice(bidDTO.getPrice());
        return b;
    }

    public Bid addBid(BidDTO bidDTO){
        Bid b = new Bid();

        final Auction a = auctionRepository.findById(bidDTO.getAuctionId());
        final Provider p = providerRepository.findById(bidDTO.getProviderId());

        b.setStatus(bidDTO.getStatus());
        b.setCurrency(bidDTO.getCurrency());
        b.setPrice(bidDTO.getPrice());
        b.setTimestamp(bidDTO.getTimestamp());
        b.setAuction(a);
        b.setProvider(p);

        try{
            bidRepository.save(b);
            return  b;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
