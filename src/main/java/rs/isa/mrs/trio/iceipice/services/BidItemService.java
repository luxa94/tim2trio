package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.AuctionItem;
import rs.isa.mrs.trio.iceipice.model.AuctionItemUnit;
import rs.isa.mrs.trio.iceipice.model.Bid;
import rs.isa.mrs.trio.iceipice.model.BidItem;
import rs.isa.mrs.trio.iceipice.model.dto.BidItemDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemRepository;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemUnitRepository;
import rs.isa.mrs.trio.iceipice.repository.BidItemRepository;
import rs.isa.mrs.trio.iceipice.repository.BidRepository;

/**
 * Created by Sandra on 21.6.2016.
 */
@Service
public class BidItemService {

    @Autowired
    AuctionItemUnitRepository auctionItemUnitRepository;

    @Autowired
    AuctionItemRepository auctionItemRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    BidItemRepository bidItemRepository;


    public BidItem updateBidItem(long id, BidItemDTO bidItemDTO){
        BidItem bi = bidItemRepository.findById(id);
        bi.setName(bidItemDTO.getName());
        bi.setPrice(bidItemDTO.getPrice());
        bi.setAuctionItemUnit(auctionItemUnitRepository.findById(bidItemDTO.getAuctionItemUnitId()));
        bi.setQuantity(bidItemDTO.getQuantity());
        return bi;
    }

    public BidItem addBidItem(BidItemDTO bidItemDTO){
        BidItem bi = new BidItem();
        final AuctionItemUnit unit = auctionItemUnitRepository.findById(bidItemDTO.getAuctionItemUnitId());
        final AuctionItem item = auctionItemRepository.findById(bidItemDTO.getAuctionItemId());
        final Bid bid  = bidRepository.findById(bidItemDTO.getBidId());

        bi.setName(bidItemDTO.getName());
        bi.setQuantity(bidItemDTO.getQuantity());
        bi.setPrice(bidItemDTO.getPrice());
        bi.setAuctionItem(item);
        bi.setAuctionItemUnit(unit);
        bi.setBid(bid);

        try{
            bidItemRepository.save(bi);
            return bi;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
