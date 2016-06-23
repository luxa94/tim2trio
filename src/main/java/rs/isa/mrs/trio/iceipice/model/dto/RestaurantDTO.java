package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.Restaurant;

import java.util.ArrayList;
import  java.util.List;

/**
 * Created by nikolalukic on 4/17/16.
 */
public class RestaurantDTO {

    private long id;

    private String name;

    private String description;

    private String pib;

    private String phoneNumber;

    private String address;

    private String email;

    private String restaurantType;

    private List<GradeDTO> restaurantGrades = new ArrayList<>();

    private int finalGradeMeal;

    private int finalGradeWaiter;

    private int finalGrade; // for restaurant

    public RestaurantDTO(Restaurant restaurant) {
        id = restaurant.getId();
        name = restaurant.getName();
        description = restaurant.getDescription();
        pib = restaurant.getPib();
        phoneNumber = restaurant.getPhoneNumber();
        address = restaurant.getAddress();
        email = restaurant.getEmail();

    }

    public RestaurantDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public List<GradeDTO> getRestaurantGrades() {
        return restaurantGrades;
    }

    public void setRestaurantGrades(List<GradeDTO> restaurantGrades) {
        this.restaurantGrades = restaurantGrades;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    public int getFinalGradeMeal() {
        return finalGradeMeal;
    }

    public void setFinalGradeMeal(int finalGradeMeal) {
        this.finalGradeMeal = finalGradeMeal;
    }

    public int getFinalGradeWaiter() {
        return finalGradeWaiter;
    }

    public void setFinalGradeWaiter(int finalGradeWaiter) {
        this.finalGradeWaiter = finalGradeWaiter;
    }
}
