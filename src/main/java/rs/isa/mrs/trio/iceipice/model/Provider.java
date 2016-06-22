package rs.isa.mrs.trio.iceipice.model;

import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * Created by Sandra on 10.4.2016.
 */

@Entity
@Table(name = "provider")
public class Provider extends BaseUser {

    public Provider() {
        this.setType(UserTypes.PROVIDER);
    }

    public Provider(String email, String password, String name, String surname, String phoneNumber, Date birthDate, String type) {
        super(email, password, name, surname, phoneNumber, birthDate, type);
    }
}
