package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Sandra on 20.6.2016.
 */
public class AuctionItemUnitDTO {
    public String name;

    public AuctionItemUnitDTO(String name) {
        this.name = name;
    }

    public AuctionItemUnitDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
