package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.OrderItem;

/**
 * Created by Nina on 21-Jun-16.
 */
public class OrderItemDTO {

    private long menuItemId;

    private int amount;

    private String name;

    public OrderItemDTO(OrderItem order) {
        this.menuItemId = order.getMenuItem().getId();
        this.amount = order.getAmount();
        this.name = order.getMenuItem().getArticle().getName();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderItemDTO() {
    }

}
