package rs.isa.mrs.trio.iceipice.model.dto;

import java.util.List;

/**
 * Created by nikolalukic on 6/19/16.
 */
public class ReservationWaiterDTO {

    private long waiterId;

    private List<Long> tableIds;

    private long restaurantId;

    private OrderItemDTO[] orderItemDTOs;

    public OrderItemDTO[] getOrderItemDTOs() {
        return orderItemDTOs;
    }

    public void setOrderItemDTOs(OrderItemDTO[] orderItemDTOs) {
        this.orderItemDTOs = orderItemDTOs;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Long> getTableIds() {
        return tableIds;
    }

    public void setTableIds(List<Long> tableIds) {
        this.tableIds = tableIds;
    }

    public long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(long waiterId) {
        this.waiterId = waiterId;
    }
}
