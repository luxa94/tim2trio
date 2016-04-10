package rs.isa.mrs.trio.iceipice.model;

import rs.isa.mrs.trio.iceipice.globals.UserTypes;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "system_manager")
public class SystemManager extends BaseUser {
    public SystemManager() {
        this.setType(UserTypes.SYSTEM_MANAGER);
    }
}
