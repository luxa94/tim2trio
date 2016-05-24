package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.ArticleType;

/**
 * Created by Sandra on 24.5.2016.
 */
public interface ArticleTypeRepository extends JpaRepository<ArticleType, Long> {
    ArticleType findById(long id);
}
