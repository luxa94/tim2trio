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
@Table(name = "waiter")
public class Waiter extends BaseUser {

    public Waiter() {
        this.setType(UserTypes.WAITER);
    }

    @Column(name = "dress_size", nullable = false)
    private String dressSize;

    @Column(name = "footwearSize", nullable = false)
    private String footwearSize;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "waiter")
    private Set<WaiterShift> waiterShifts;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Order> orders;

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

    public Set<WaiterShift> getWaiterShifts() {
        return waiterShifts;
    }

    public void setWaiterShifts(Set<WaiterShift> waiterShifts) {
        this.waiterShifts = waiterShifts;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Waiter(String email, String password, String name, String surname, String phoneNumber, Date birthDate, String type, String dressSize, String footwearSize) {
        super(email, password, name, surname, phoneNumber, birthDate, type);
        this.dressSize = dressSize;
        this.footwearSize = footwearSize;
    }
}
