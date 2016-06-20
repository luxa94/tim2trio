package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sandra on 20.6.2016.
 */
@RestController
@RequestMapping("/api")
public class AuctionItemTypeController {

    public String name;

    public AuctionItemTypeController(String name) {
        this.name = name;
    }

    public AuctionItemTypeController() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
