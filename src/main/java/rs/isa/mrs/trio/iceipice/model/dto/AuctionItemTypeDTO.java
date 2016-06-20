package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Sandra on 20.6.2016.
 */
public class AuctionItemTypeDTO {
    public String name;

    public AuctionItemTypeDTO(String name) {
        this.name = name;
    }

    public AuctionItemTypeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
