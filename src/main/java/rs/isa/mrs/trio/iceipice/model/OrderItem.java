package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}