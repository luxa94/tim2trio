package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Sandra on 19.6.2016.
 */
public class AuctionItemDTO {
    public String name;
    public long quantity;
    public long auctionId;
    public long auctionItemTypeId;
    public long auctionItemUnitId;

    public AuctionItemDTO(String name, long quantity, long auctionId, long auctionItemTypeId, long auctionItemUnitId) {
        this.name = name;
        this.quantity = quantity;
        this.auctionId = auctionId;
        this.auctionItemTypeId = auctionItemTypeId;
        this.auctionItemUnitId = auctionItemUnitId;
    }

    public AuctionItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }

    public long getAuctionItemTypeId() {
        return auctionItemTypeId;
    }

    public void setAuctionItemTypeId(long auctionItemTypeId) {
        this.auctionItemTypeId = auctionItemTypeId;
    }

    public long getAuctionItemUnitId() {
        return auctionItemUnitId;
    }

    public void setAuctionItemUnitId(long auctionItemUnitId) {
        this.auctionItemUnitId = auctionItemUnitId;
    }
}
