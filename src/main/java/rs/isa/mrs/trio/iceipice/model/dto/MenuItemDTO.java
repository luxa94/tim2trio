package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Sandra on 25.5.2016.
 */
public class MenuItemDTO {
    private double price;

    private long articleId;

    private long menuId;

    public MenuItemDTO() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }
}
