package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Nina on 23-May-16.
 */
public class InviteFriendDTO {

    protected long myId;

    protected String email;

    public InviteFriendDTO() {}

    public long getMyId() {
        return myId;
    }

    public void setMyId(long myId) {
        this.myId = myId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
