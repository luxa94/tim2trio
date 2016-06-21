package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.AuctionItemUnit;
import rs.isa.mrs.trio.iceipice.model.dto.AuctionItemUnitDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemUnitRepository;
import rs.isa.mrs.trio.iceipice.services.AuctionItemUnitService;

/**
 * Created by Sandra on 20.6.2016.
 */
@RestController
@RequestMapping("/api")
public class AuctionItemUnitController {
    @Autowired
    AuctionItemUnitRepository auctionItemUnitRepository;

    @Autowired
    AuctionItemUnitService auctionItemUnitService;

    @RequestMapping(value = "/auctionItemUnit/all", method = RequestMethod.GET)
    public ResponseEntity getAllAuctionItemUnits() {
        return new ResponseEntity<>(auctionItemUnitRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/auctionItemUnit/new", method = RequestMethod.POST)
    public ResponseEntity addAuctionItemUnit(@RequestBody AuctionItemUnitDTO auctionItemUnitDTO) {
        AuctionItemUnit unit = auctionItemUnitService.addUnit(auctionItemUnitDTO);
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }
}
