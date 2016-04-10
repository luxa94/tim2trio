package rs.isa.mrs.trio.iceipice.model;


import javax.persistence.*;
import javax.websocket.ClientEndpoint;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "meal_grade")
    private int meal_grade;

    @Column(name = "meal_comment")
    private String meal_comment;

    @Column(name = "waiter_grade")
    private int waiter_grade;

    @Column(name = "waiter_comment")
    private String waiter_comment;

    @Column(name = "atmosphere_grade")
    private int atmosphere_grade;

    @Column(name = "atmosphere_comment")
    private String atmosphere_comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Guest guest;

    @ManyToOne(fetch = FetchType.EAGER)
    private Reservation reservation;

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMeal_grade() {
        return meal_grade;
    }

    public void setMeal_grade(int meal_grade) {
        this.meal_grade = meal_grade;
    }

    public String getMeal_comment() {
        return meal_comment;
    }

    public void setMeal_comment(String meal_comment) {
        this.meal_comment = meal_comment;
    }

    public int getWaiter_grade() {
        return waiter_grade;
    }

    public void setWaiter_grade(int waiter_grade) {
        this.waiter_grade = waiter_grade;
    }

    public String getWaiter_comment() {
        return waiter_comment;
    }

    public void setWaiter_comment(String waiter_comment) {
        this.waiter_comment = waiter_comment;
    }

    public int getAtmosphere_grade() {
        return atmosphere_grade;
    }

    public void setAtmosphere_grade(int atmosphere_grade) {
        this.atmosphere_grade = atmosphere_grade;
    }

    public String getAtmosphere_comment() {
        return atmosphere_comment;
    }

    public void setAtmosphere_comment(String atmosphere_comment) {
        this.atmosphere_comment = atmosphere_comment;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
