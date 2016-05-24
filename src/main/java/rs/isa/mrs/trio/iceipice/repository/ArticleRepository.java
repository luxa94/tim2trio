package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Article;

/**
 * Created by Sandra on 24.5.2016.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findById(long id);
}
