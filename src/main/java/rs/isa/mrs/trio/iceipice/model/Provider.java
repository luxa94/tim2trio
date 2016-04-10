package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "provider")
public class Provider extends BaseUser {

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Bid> bids;

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}
