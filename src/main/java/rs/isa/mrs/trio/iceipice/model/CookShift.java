package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by nikolalukic on 5/24/16.
 */
@Entity
@Table(name = "cook_shift")
public class CookShift {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "cook", nullable = false)
    private Cook cook;

    @ManyToOne
    @JoinColumn(name = "shift", nullable = false)
    private Shift shift;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
