package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Nina on 21-Jun-16.
 */
public class OrderItemDTO {

    private long menuItemId;

    private int amount;

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
