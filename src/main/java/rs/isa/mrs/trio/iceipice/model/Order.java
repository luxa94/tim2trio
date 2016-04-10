package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "status")
    private String status;
}
