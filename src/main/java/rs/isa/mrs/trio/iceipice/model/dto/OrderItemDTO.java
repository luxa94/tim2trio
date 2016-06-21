package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.OrderItem;

/**
 * Created by Nina on 21-Jun-16.
 */
public class OrderItemDTO {

    private long menuItemId;

    private int amount;

    public OrderItemDTO(OrderItem order) {
        this.menuItemId = order.getMenuItem().getId();
        this.amount = order.getAmount();
    }

    public long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OrderItemDTO() {
    }

}
