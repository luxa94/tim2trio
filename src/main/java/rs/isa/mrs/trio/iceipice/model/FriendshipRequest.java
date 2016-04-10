package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "friendship_request")
public class FriendshipRequest {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Guest from_user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Guest to_user;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "status", nullable = false)
    private boolean status;

    public Guest getTo_user() {
        return to_user;
    }

    public void setTo_user(Guest to_user) {
        this.to_user = to_user;
    }

    public Guest getFrom_user() {
        return from_user;
    }

    public void setFrom_user(Guest from_user) {
        this.from_user = from_user;
    }

}
