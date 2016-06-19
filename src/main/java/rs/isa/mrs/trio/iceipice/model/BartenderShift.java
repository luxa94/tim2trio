package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by nikolalukic on 5/24/16.
 */
@Entity
@Table(name = "bartender_shift")
public class BartenderShift {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "bartender", nullable = false)
    private Bartender bartender;

    @ManyToOne
    @JoinColumn(name = "shift", nullable = false)
    private Shift shift;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bartender getBartender() {
        return bartender;
    }

    public void setBartender(Bartender bartender) {
        this.bartender = bartender;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public BartenderShift(Bartender bartender, Shift shift) {
        this.bartender = bartender;
        this.shift = shift;
    }
}
