package rs.isa.mrs.trio.iceipice.model.dto;

import java.util.Date;

/**
 * Created by Nina on 17-Jun-16.
 */
public class GetReservationDTO {

    long restaurantId;

    Date reservationDate;

    public GetReservationDTO() {

    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
