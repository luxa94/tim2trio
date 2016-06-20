package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Auction;
import rs.isa.mrs.trio.iceipice.model.AuctionItem;
import rs.isa.mrs.trio.iceipice.model.dto.AuctionDTO;
import rs.isa.mrs.trio.iceipice.model.dto.AuctionItemDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemRepository;
import rs.isa.mrs.trio.iceipice.repository.AuctionRepository;
import rs.isa.mrs.trio.iceipice.services.AuctionItemService;
import rs.isa.mrs.trio.iceipice.services.AuctionService;

import java.util.List;

/**
 * Created by Sandra on 19.6.2016.
 */
@RestController
@RequestMapping("/api")
public class AuctionController {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AuctionItemService auctionItemService;

    @Autowired
    AuctionService auctionService;

    @RequestMapping(value = "/auction/all", method = RequestMethod.GET)
    public ResponseEntity getAllAuctions() {
        return new ResponseEntity<>(auctionRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/auctionItem/newAuctionItem", method = RequestMethod.POST)
    public ResponseEntity addAuctionItem(@RequestBody AuctionItemDTO auctionItemDTO) {
        AuctionItem ai = auctionItemService.addAuctionItem(auctionItemDTO);
        return new ResponseEntity<>(ai, HttpStatus.OK);
    }

    @RequestMapping(value = "/auction/newAuction", method = RequestMethod.POST)
    public ResponseEntity addAuction(@RequestBody AuctionDTO auctionDTO) {
        Auction a = auctionService.addAuction(auctionDTO);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @RequestMapping(value = "/auction/allItemsFromAuction/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllAuctionItemsFromAuction(@PathVariable long id){
        List<AuctionItem> list = auctionItemService.getAuctionItemFromAuctionId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
