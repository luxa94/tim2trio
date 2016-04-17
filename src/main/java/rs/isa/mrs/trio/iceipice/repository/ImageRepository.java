package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Image;

import java.util.List;

/**
 * Created by Sandra on 17.4.2016.
 */
public interface ImageRepository  extends JpaRepository<Image, Long> {

    Image findById(long id);

    List<Image> findByUrl(String url);
}
