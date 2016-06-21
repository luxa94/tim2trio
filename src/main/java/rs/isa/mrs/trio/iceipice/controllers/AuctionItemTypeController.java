package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.AuctionItem;
import rs.isa.mrs.trio.iceipice.model.AuctionItemType;
import rs.isa.mrs.trio.iceipice.model.dto.AuctionItemTypeDTO;
import rs.isa.mrs.trio.iceipice.repository.AuctionItemTypeRepository;
import rs.isa.mrs.trio.iceipice.services.AuctionItemTypeService;

/**
 * Created by Sandra on 20.6.2016.
 */
@RestController
@RequestMapping("/api")
public class AuctionItemTypeController {

    @Autowired
    AuctionItemTypeRepository auctionItemTypeRepository;

    @Autowired
    AuctionItemTypeService auctionItemTypeService;

    @RequestMapping(value = "/auctionItemType/all", method = RequestMethod.GET)
    public ResponseEntity getAllAuctionItemTypes() {
        return new ResponseEntity<>(auctionItemTypeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/auctionItemType/new", method = RequestMethod.POST)
    public ResponseEntity addAuctionItemType(@RequestBody AuctionItemTypeDTO auctionItemTypeDTO) {
        AuctionItemType type = auctionItemTypeService.addType(auctionItemTypeDTO);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }
}
