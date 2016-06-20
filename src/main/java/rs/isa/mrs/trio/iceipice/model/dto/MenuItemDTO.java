package rs.isa.mrs.trio.iceipice.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandra on 25.5.2016.
 */
public class MenuItemDTO {

    private double price;
    private String articleName;
    private long articleTypeId;
    private String articleDescription;
    private long menuId;

    private List<GradeDTO> menuItemGrades = new ArrayList<>();

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public MenuItemDTO() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public long getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(long articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public List<GradeDTO> getMenuItemGrades() {
        return menuItemGrades;
    }

    public void setMenuItemGrades(List<GradeDTO> menuItemGrades) {
        this.menuItemGrades = menuItemGrades;
    }
}
