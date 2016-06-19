package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import rs.isa.mrs.trio.iceipice.model.OrderItem;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by nikolalukic on 6/18/16.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<OrderItem> findByMenuItem_Article_ArticleType_Name(String name);

}
