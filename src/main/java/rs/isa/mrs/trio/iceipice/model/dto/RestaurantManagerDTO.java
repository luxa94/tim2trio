package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by nikolalukic on 4/22/16.
 */
public class RestaurantManagerDTO extends BaseUserDTO{

    private long restaurantId;

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

}
