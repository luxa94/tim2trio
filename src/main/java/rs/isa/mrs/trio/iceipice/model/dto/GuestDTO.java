package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.Guest;

/**
 * Created by Nina on 17-Apr-16.
 */
public class GuestDTO extends BaseUserDTO {

    private boolean isFriend;

    public GuestDTO(Guest guest){
        id = guest.getId();
        name = guest.getName();
        surname = guest.getSurname();
        birthDate = guest.getBirthDate();
        phoneNumber = guest.getPhoneNumber();
        isFriend = false;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

}
