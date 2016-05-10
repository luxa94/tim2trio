package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.Guest;

import java.util.Date;

/**
 * Created by Nina on 17-Apr-16.
 */
public class GuestDTO {

    private long id;

    private String name;

    private String surname;

    private Date birthDate;

    private String phoneNumber;

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



    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
