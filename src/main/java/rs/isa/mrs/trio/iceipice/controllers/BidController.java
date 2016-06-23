package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Bid;
import rs.isa.mrs.trio.iceipice.model.BidItem;
import rs.isa.mrs.trio.iceipice.model.dto.BidDTO;
import rs.isa.mrs.trio.iceipice.model.dto.BidItemDTO;
import rs.isa.mrs.trio.iceipice.repository.BidItemRepository;
import rs.isa.mrs.trio.iceipice.repository.BidRepository;
import rs.isa.mrs.trio.iceipice.services.BidItemService;
import rs.isa.mrs.trio.iceipice.services.BidService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandra on 21.6.2016.
 */
@RestController
@RequestMapping("/api")
public class BidController {

    @Autowired
    BidItemService bidItemService;

    @Autowired
    BidService bidService;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    BidItemRepository bidItemRepository;

    @RequestMapping(value = "/bidItem/new", method = RequestMethod.POST)
    public ResponseEntity addBidItem(@RequestBody BidItemDTO bidItemDTO) {
        BidItem bi = bidItemService.addBidItem(bidItemDTO);
        return new ResponseEntity<>(bi, HttpStatus.OK);
    }

    @RequestMapping(value = "/bid/new", method = RequestMethod.POST)
    public ResponseEntity addBid(@RequestBody BidDTO bidDTO) {
        Bid b = bidService.addBid(bidDTO);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @RequestMapping(value = "/bid/update/{bidId}", method = RequestMethod.POST)
    public ResponseEntity updateBid(@PathVariable long bidId,@RequestBody BidDTO bidDTO) {
        Bid b = bidService.updateBid(bidId,bidDTO);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @RequestMapping(value = "/bidItem/update/{bidItemId}", method = RequestMethod.POST)
    public ResponseEntity updateBidItem(@PathVariable long bidItemId,@RequestBody BidItemDTO bidItemDTO) {
        BidItem bi = bidItemService.updateBidItem(bidItemId,bidItemDTO);
        return new ResponseEntity<>(bi, HttpStatus.OK);
    }

    @RequestMapping(value = "/bid/getBidFromAuctionIdAndProviderId/{auctionId}/{providerId}", method = RequestMethod.GET)
    public ResponseEntity getBidFromAuctionIdAndProviderId(@PathVariable long auctionId,@PathVariable long providerId){
        List<Bid> bids = bidRepository.findAll();
        for(Bid b : bids){
            if (b.getAuction().getId() == auctionId && b.getProvider().getId() == providerId){
                return new ResponseEntity<>(b, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/bid/getBidsFromAuctionId/{auctionId}", method = RequestMethod.GET)
    public ResponseEntity getBidFromAuctionId(@PathVariable long auctionId){
        List<Bid> bids = bidRepository.findAll();
        List<Bid> retval = new ArrayList<Bid>();
        for(Bid b : bids){
            if (b.getAuction().getId() == auctionId){
                bids.add(b);
            }
        }
        return new ResponseEntity<>(retval, HttpStatus.OK);
    }

    @RequestMapping(value = "/bid/getBidItems/{id}", method = RequestMethod.GET)
    public ResponseEntity getBidItemsFromBidId(@PathVariable long id){
        List<BidItem> bidItems = bidItemRepository.findAll();
        List<BidItem> retVal = new ArrayList<BidItem>();
        for(BidItem bi : bidItems){
            if(bi.getBid().getId() == id){
                retVal.add(bi);
            }
        }
        return  new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    @RequestMapping(value = "/bid/activateBid", method = RequestMethod.POST)
    public ResponseEntity activateBid(@RequestBody BidDTO bidDTO){

        //dobavi bid
        Bid bid;
        List<Bid> bids = bidRepository.findAll();
        for(Bid b : bids){
            if (b.getAuction().getId() == bidDTO.getAuctionId() && b.getProvider().getId() == bidDTO.getProviderId()){
                b.setStatus("aktivna");
                bidRepository.save(b);
                return new ResponseEntity<>(b, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.OK);


    }

    @RequestMapping(value = "/bid/getActiveBidsFromProvider/{id}", method = RequestMethod.GET)
    public ResponseEntity getActiveBidsFromProvider(@PathVariable long id){
        List<Bid> bids = bidRepository.findAll();
        List<Bid> retVal = new ArrayList<Bid>();
        for(Bid b : bids){
            if(b.getProvider().getId() == id && b.getStatus().equals("aktivna")){
                retVal.add(b);
            }
        }
        return  new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    @RequestMapping(value = "/bid/getBidsFromProvider/{id}", method = RequestMethod.GET)
    public ResponseEntity getBidsFromProvider(@PathVariable long id){
        List<Bid> bids = bidRepository.findAll();
        List<Bid> retVal = new ArrayList<Bid>();
        for(Bid b : bids){
            if(b.getProvider().getId() == id){
                retVal.add(b);
            }
        }
        return  new ResponseEntity<>(retVal,HttpStatus.OK);
    }
}
