package rs.isa.mrs.trio.iceipice.model;

import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.*;
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

    @Column(name = "referralCode", nullable = false, unique = true)
    private String referralCode;

    @Column(name = "confirmed", nullable = false)
    private Boolean confirmed;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Invitation> invitations;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Grade> grades;

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
}
