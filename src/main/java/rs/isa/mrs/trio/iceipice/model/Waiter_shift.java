package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "Waiter_shift" )
public class Waiter_shift {

    @Id
    @GeneratedValue
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
