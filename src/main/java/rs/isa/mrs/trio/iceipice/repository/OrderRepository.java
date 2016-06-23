package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.Order;
import rs.isa.mrs.trio.iceipice.model.OrderItem;
import rs.isa.mrs.trio.iceipice.model.Reservation;

import java.util.List;

/**
 * Created by Nina on 21-Jun-16.
 */
public interface OrderRepository   extends JpaRepository<Order, Long> {
    public Order findById(long id);

    List<Order> findByReservation(Reservation reservation);
}
