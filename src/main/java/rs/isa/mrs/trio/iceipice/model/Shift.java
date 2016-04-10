package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by Nina on 10-Apr-16.
 */
@Entity
@Table(name = "shift")
public class Shift {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "shift_type", nullable = false)
    private String shift_type;

    @Column(name = "active", nullable = false)
    private Boolean active;
}
