package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "article_type")
public class ArticleType {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArticleType() {
    }

    public ArticleType(String name) {
        this.name = name;
    }
}
