package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Sandra on 17.4.2016.
 */
@Entity
@Table(name = "Image")
public class Image {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(name = "url", unique = false, nullable = false)
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
