package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "auction_item_type")
public class AuctionItemType {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<AuctionItem> auctionItems;

    @ManyToOne(fetch = FetchType.EAGER)
    private BidItem bidItem;

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

    public BidItem getBidItem() {
        return bidItem;
    }

    public void setBidItem(BidItem bidItem) {
        this.bidItem = bidItem;
    }
}