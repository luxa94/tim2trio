package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.AuctionItemUnit;
import rs.isa.mrs.trio.iceipice.model.dto.AuctionItemUnitDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemUnitRepository;

/**
 * Created by Sandra on 20.6.2016.
 */
@Service
public class AuctionItemUnitService {
    @Autowired
    AuctionItemUnitRepository auctionItemUnitRepository;

    public AuctionItemUnit addUnit(AuctionItemUnitDTO auctionItemUnitDTO){
        AuctionItemUnit unit = new AuctionItemUnit();
        unit.setName(auctionItemUnitDTO.getName());

        try{
            auctionItemUnitRepository.save(unit);
            return unit;
        }catch (Exception e){
            System.out.print(e);
            return null;
        }
    }
}
