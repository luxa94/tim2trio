package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.OrderItem;
import rs.isa.mrs.trio.iceipice.model.Restaurant;

/**

 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findById(long id);

}
