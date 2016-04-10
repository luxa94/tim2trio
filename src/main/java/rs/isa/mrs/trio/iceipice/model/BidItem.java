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
    private int quantity;

    @Column(name = "price", nullable =  false)
    private long price;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bid bid;

    @ManyToOne(fetch = FetchType.EAGER)
    private AuctionItemType auctionItemType;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public AuctionItemType getAuctionItemType() {
        return auctionItemType;
    }

    public void setAuctionItemType(AuctionItemType auctionItemType) {
        this.auctionItemType = auctionItemType;
    }
}
