package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "price")
    private double price;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider", nullable = false)
    private Provider provider;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @Column(name = "currency")
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Bid() {
    }


}
