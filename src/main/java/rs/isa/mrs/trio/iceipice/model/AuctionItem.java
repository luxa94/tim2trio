package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "auction_item")
public class AuctionItem {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Auction auction;

    @ManyToOne(fetch = FetchType.EAGER)
    private AuctionItemType auctionItemType;

    @ManyToOne(fetch = FetchType.EAGER)
    private AuctionItemUnit auctionItemUnit;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public AuctionItemUnit getAuctionItemUnit() {
        return auctionItemUnit;
    }

    public void setAuctionItemUnit(AuctionItemUnit auctionItemUnit) {
        this.auctionItemUnit = auctionItemUnit;
    }

    public AuctionItemType getAuctionItemType() {
        return auctionItemType;
    }

    public void setAuctionItemType(AuctionItemType auctionItemType) {
        this.auctionItemType = auctionItemType;
    }
}
