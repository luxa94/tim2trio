package rs.isa.mrs.trio.iceipice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "bartender")
public class Bartender extends BaseUser {

    public Bartender() {
        this.setType(UserTypes.BARTENDER);
    }

    @Column(name = "dress_size", nullable = false)
    private String dressSize;

    @Column(name = "footwearSize", nullable = false)
    private String footwearSize;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bartenders")
    private Set<OrderItem> orderItems;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bartenders")
    private Set<Shift> shifts;

    public String getDressSize() {
        return dressSize;
    }

    public void setDressSize(String dressSize) {
        this.dressSize = dressSize;
    }

    public String getFootwearSize() {
        return footwearSize;
    }

    public void setFootwearSize(String footwearSize) {
        this.footwearSize = footwearSize;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public Bartender(String email, String password, String name, String surname, String phoneNumber, Date birthDate, String footwearSize, String dressSize) {
        super(email, password, name, surname, phoneNumber, birthDate, UserTypes.BARTENDER);
        this.footwearSize = footwearSize;
        this.dressSize = dressSize;
    }


}
