package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.OrderItem;
import rs.isa.mrs.trio.iceipice.repository.OrderItemRepository;

import java.util.List;

/**
 * Created by nikolalukic on 6/18/16.
 */
@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    // TODO: change
    public List<OrderItem> findByRestaurant() {
        return orderItemRepository.findAll();
    }

}
