package rs.isa.mrs.trio.iceipice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ninasimic on 10-Apr-16.
 */
@Entity
@Table(name="guest")
public class Guest extends BaseUser{

    public Guest() {
        this.setType(UserTypes.GUEST);
    }

    public Guest(String email, String password, String name, String surname, String phoneNumber, Date birthDate) {
        super(email, password, name, surname, phoneNumber, birthDate, UserTypes.GUEST);
        this.confirmed = false;
    }

    @Column(name = "referral_code", nullable = false, unique = true)
    private String referralCode;

    @Column(name = "confirmed", nullable = false)
    private Boolean confirmed;

    @JsonBackReference("guest-invitations")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guest", orphanRemoval=true)
    private Set<Invitation> invitations;

    @JsonBackReference("guest-reservations")
   // @OneToMany(fetch = FetchType.LAZY, mappedBy = "guest", orphanRemoval=true)
    @ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JoinTable(name = "guest_reservation_",
           joinColumns = {@JoinColumn(name = "guest_id",  nullable = false)},
           inverseJoinColumns = {@JoinColumn(name = "reservation_id",  nullable = false)})
    private Set<Reservation> reservations;

    @JsonBackReference("guest-grades")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guest", orphanRemoval=true)
    private Set<Grade> grades;

    @JsonBackReference("guest-guest")
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Guest> friends;

    @PrePersist
    protected void ensureReferralCode() {
        referralCode = UUID.randomUUID().toString();
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }


    public Set<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<Invitation> invitations) {
        this.invitations = invitations;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Set<Guest> getFriends() {
        return friends;
    }

    public void setFriends(Set<Guest> friends) {
        this.friends = friends;
    }

    public boolean isFriendsWith(Guest potentialFriend) {
        for(Guest guest: friends){
            if(potentialFriend.getId() == guest.getId()){
                return true;
            }
        }
        return false;
    }
}
