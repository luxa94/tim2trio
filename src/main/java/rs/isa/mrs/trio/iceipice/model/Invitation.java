package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by Nina on 10-Apr-16.
 */

@Entity
@Table(name = "invitation")
public class Invitation {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
