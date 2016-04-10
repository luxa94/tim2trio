package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

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
