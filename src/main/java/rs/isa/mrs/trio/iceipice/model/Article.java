package rs.isa.mrs.trio.iceipice.model;

import javax.persistence.*;

/**
 * Created by nikolalukic on 4/10/16.
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_type_id", nullable = false)
    private ArticleType articleType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public Article() {

    }

    public Article(String name, String description, ArticleType articleType) {
        this.name = name;
        this.description = description;
        this.articleType = articleType;
    }
}
