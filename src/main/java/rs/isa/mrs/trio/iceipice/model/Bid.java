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
    private String price;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "status", nullable = false)
    private String status;

    public long getId() {
        return id;
    }

    public String getPrice() {
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

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
