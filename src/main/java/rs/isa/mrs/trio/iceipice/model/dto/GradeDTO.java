package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.Grade;

/**
 * Created by Nina on 19-Jun-16.
 */
public class GradeDTO {


    private int meal_grade;

    private String meal_comment;

    private int waiter_grade;

    private String waiter_comment;

    private int atmosphere_grade;

    private String atmosphere_comment;

    private long userId;

    private long reservationId;

    public GradeDTO(Grade grade) {
        meal_grade = grade.getMeal_grade();
        waiter_grade = grade.getWaiter_grade();
        atmosphere_grade = grade.getAtmosphere_grade();
        meal_comment = grade.getMeal_comment();
        waiter_comment = grade.getWaiter_comment();
        atmosphere_comment = grade.getAtmosphere_comment();
        userId = grade.getGuest().getId();
        reservationId = grade.getReservation().getId();
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAtmosphere_comment() {
        return atmosphere_comment;
    }

    public void setAtmosphere_comment(String atmosphere_comment) {
        this.atmosphere_comment = atmosphere_comment;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public GradeDTO() { }
}
