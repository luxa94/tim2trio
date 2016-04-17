package rs.isa.mrs.trio.iceipice.model;

import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "system_manager")
public class SystemManager extends BaseUser {

    public SystemManager() {
        this.setType(UserTypes.SYSTEM_MANAGER);
    }

    public SystemManager(String email, String password, String name, String surname, String phoneNumber, Date birthDate) {
        super(email, password, name, surname, phoneNumber, birthDate, UserTypes.SYSTEM_MANAGER);
    }

}
