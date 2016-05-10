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

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_user_id", nullable = false)
    private Guest fromUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_user_id", nullable = false)
    private Guest toUser;

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

    public Guest getToUser() {
        return toUser;
    }

    public void setToUser(Guest toUser) {
        this.toUser = toUser;
    }

    public Guest getFromUser() {
        return fromUser;
    }

    public void setFromUser(Guest fromUser) {
        this.fromUser = fromUser;
    }

    public FriendshipRequest() {


    }
}
