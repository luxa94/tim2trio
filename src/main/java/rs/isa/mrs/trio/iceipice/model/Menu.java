package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<MenuItem> menuItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
