package rs.isa.mrs.trio.iceipice.model;

import rs.isa.mrs.trio.iceipice.globals.UserTypes;

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

    public Provider() {
        this.setType(UserTypes.PROVIDER);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
    private Set<Bid> bids;

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}
