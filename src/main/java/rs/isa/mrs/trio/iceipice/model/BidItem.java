package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "bid_item")
public class BidItem {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    @Column(name = "price", nullable =  false)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bid_id", nullable = false)
    private Bid bid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auction_item_unit_id", nullable = false)
    private AuctionItemUnit auctionItemUnit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auction_item_id", nullable = false)
    private AuctionItem auctionItem;

    public BidItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public AuctionItemUnit getAuctionItemUnit() {
        return auctionItemUnit;
    }

    public void setAuctionItemUnit(AuctionItemUnit auctionItemUnit) {
        this.auctionItemUnit = auctionItemUnit;
    }

    public AuctionItem getAuctionItem() {
        return auctionItem;
    }

    public void setAuctionItem(AuctionItem auctionItem) {
        this.auctionItem = auctionItem;
    }
}
