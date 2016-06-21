package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.AuctionItemType;
import rs.isa.mrs.trio.iceipice.model.dto.AuctionItemTypeDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemTypeRepository;

/**
 * Created by Sandra on 20.6.2016.
 */
@Service
public class AuctionItemTypeService {

    @Autowired
    AuctionItemTypeRepository auctionItemTypeRepository;

    public AuctionItemType addType(AuctionItemTypeDTO auctionItemTypeDTO){
        AuctionItemType auctionItemType = new AuctionItemType();
        auctionItemType.setName(auctionItemTypeDTO.getName());

        try{
            auctionItemTypeRepository.save(auctionItemType);
            return auctionItemType;
        }catch (Exception e){
            System.out.print(e);
            return null;
        }
    }
}
