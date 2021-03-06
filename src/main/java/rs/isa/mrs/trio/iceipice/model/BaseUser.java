package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "base_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseUser {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "type", nullable = false)
    private String type;

    public BaseUser() {

    }

    public BaseUser(String email, String password, String name, String surname, String phoneNumber, Date birthDate, String type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
