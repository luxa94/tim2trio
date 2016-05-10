package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Nina on 10-May-16.
 */
public class AddFriendDTO {
    long myId;
    long friendId;

    public AddFriendDTO() {

    }

    public long getMyId() {
        return myId;
    }

    public void setMyId(long myId) {
        this.myId = myId;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }
}
