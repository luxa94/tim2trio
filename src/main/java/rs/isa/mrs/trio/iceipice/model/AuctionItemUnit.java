package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "auction_item_unit")
public class AuctionItemUnit {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auctionItemUnit")
    private Set<AuctionItem> auctionItems;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auctionItemUnit")
    private Set<BidItem> bidItems;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AuctionItem> getAuctionItems() {
        return auctionItems;
    }

    public void setAuctionItems(Set<AuctionItem> auctionItems) {
        this.auctionItems = auctionItems;
    }

    public Set<BidItem> getBidItems() {
        return bidItems;
    }

    public void setBidItems(Set<BidItem> bidItems) {
        this.bidItems = bidItems;
    }

    public AuctionItemUnit() {
        auctionItems = new HashSet<>();
        bidItems = new HashSet<>();
    }
}
