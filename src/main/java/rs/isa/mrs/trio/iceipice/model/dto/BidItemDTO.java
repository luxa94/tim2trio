package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Sandra on 21.6.2016.
 */
public class BidItemDTO {
    public String name;
    public double quantity;
    public double price;
    public long auctionItemUnitId;
    public long auctionItemId;
    public long bidId;

    public BidItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getAuctionItemUnitId() {
        return auctionItemUnitId;
    }

    public void setAuctionItemUnitId(long auctionItemUnitId) {
        this.auctionItemUnitId = auctionItemUnitId;
    }

    public long getAuctionItemId() {
        return auctionItemId;
    }

    public void setAuctionItemId(long auctionItemId) {
        this.auctionItemId = auctionItemId;
    }

    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }
}
