package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "from_date", nullable = false)
    private Date from_date;

    @Column(name = "to_date", nullable = false)
    private Date to_date;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Bid> bids;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<AuctionItem> auctionItems;

    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public long getId() {
        return id;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<AuctionItem> getAuctionItems() {
        return auctionItems;
    }

    public void setAuctionItems(Set<AuctionItem> auctionItems) {
        this.auctionItems = auctionItems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
