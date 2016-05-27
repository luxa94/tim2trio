package rs.isa.mrs.trio.iceipice.services;

import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import rs.isa.mrs.trio.iceipice.model.MenuItem;
import rs.isa.mrs.trio.iceipice.model.dto.MenuItemDTO;
import rs.isa.mrs.trio.iceipice.repository.ArticleRepository;
import rs.isa.mrs.trio.iceipice.repository.MenuItemRepository;
import rs.isa.mrs.trio.iceipice.repository.MenuRepository;

/**
 * Created by Sandra on 27.5.2016.
 */
@Serialization
public class MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ArticleRepository articleRepository;

    public MenuItem addMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();
        updateMenuItem(menuItem, menuItemDTO);

        try {
            menuItem = menuItemRepository.save(menuItem);
            return menuItem;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateMenuItem(MenuItem menuItem, MenuItemDTO menuItemDTO) {
        menuItem.setMenu(menuRepository.findById(menuItemDTO.getMenuId()));
        menuItem.setArticle(articleRepository.findById(menuItemDTO.getArticleId()));

    }

}
