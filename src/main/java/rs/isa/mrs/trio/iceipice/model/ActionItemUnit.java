package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "auction_item_unit")
public class ActionItemUnit {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
