package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Auction;
import rs.isa.mrs.trio.iceipice.model.AuctionItem;
import rs.isa.mrs.trio.iceipice.model.AuctionItemType;
import rs.isa.mrs.trio.iceipice.model.AuctionItemUnit;
import rs.isa.mrs.trio.iceipice.model.dto.AuctionItemDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemRepository;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemTypeRepository;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemUnitRepository;
import rs.isa.mrs.trio.iceipice.repository.AuctionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sandra on 19.6.2016.
 */
@Service
public class AuctionItemService {
    @Autowired
    AuctionItemRepository auctionItemRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AuctionItemTypeRepository auctionItemTypeRepository;

    @Autowired
    AuctionItemUnitRepository auctionItemUnitRepository;


    public AuctionItem addAuctionItem(AuctionItemDTO auctionItemDTO) {
        AuctionItem auctionItem = new AuctionItem();
        Auction a = auctionRepository.findById(auctionItemDTO.getAuctionId());
        AuctionItemType auctionItemType = auctionItemTypeRepository.findById(auctionItemDTO.getAuctionItemTypeId());
        AuctionItemUnit auctionItemUnit = auctionItemUnitRepository.findById(auctionItemDTO.getAuctionItemUnitId());

        auctionItem.setName(auctionItemDTO.getName());
        auctionItem.setQuantity(auctionItemDTO.getQuantity());
        auctionItem.setAuction(a);
        auctionItem.setAuctionItemType(auctionItemType);
        auctionItem.setAuctionItemUnit(auctionItemUnit);

        try {
            auctionItemRepository.save(auctionItem);
            return auctionItem;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }

    public List<AuctionItem> getAuctionItemFromAuctionId(long auctionId){
        System.out.print("USAO U METODU U SERVISU");
        List<AuctionItem> auctionItemList = new ArrayList<AuctionItem>();
        List<AuctionItem> allItems = auctionItemRepository.findAll();

        for (AuctionItem ai : allItems) {
            if(ai.getAuction().getId() == auctionId)
                auctionItemList.add(ai);
        }

        return auctionItemList;
    }
}
