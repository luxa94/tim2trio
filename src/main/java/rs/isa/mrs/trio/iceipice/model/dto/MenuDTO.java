package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Sandra on 25.5.2016.
 */
public class MenuDTO {

    private long id;
    private long restaurantId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public MenuDTO() {
    }
}
