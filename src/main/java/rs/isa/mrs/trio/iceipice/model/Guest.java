package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ninasimic on 10-Apr-16.
 */
@Entity
@Table(name="guest")
public class Guest extends BaseUser{


    @Column(name = "referral_code", nullable = false, unique = true)
    @GeneratedValue
    private String referral_code;

    @Column(name = "confirmed", nullable = false)
    private Boolean confirmed;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Invitation> invitations;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Grade> grades;

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

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
