package rs.isa.mrs.trio.iceipice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Article;
import rs.isa.mrs.trio.iceipice.model.ArticleType;
import rs.isa.mrs.trio.iceipice.model.MenuItem;
import rs.isa.mrs.trio.iceipice.model.dto.MenuItemDTO;
import rs.isa.mrs.trio.iceipice.repository.ArticleRepository;
import rs.isa.mrs.trio.iceipice.repository.ArticleTypeRepository;
import rs.isa.mrs.trio.iceipice.repository.MenuItemRepository;
import rs.isa.mrs.trio.iceipice.repository.MenuRepository;

/**
 * Created by Sandra on 27.5.2016.
 */
@Service
public class MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleTypeRepository articleTypeRepository;

    public MenuItem addMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();
        ArticleType at = articleTypeRepository.findById(menuItemDTO.getArticleTypeId());
        final Article a = new Article(menuItemDTO.getArticleName(),menuItemDTO.getArticleDescription(),at);
        menuItem.setArticle(a);

        try {
            articleRepository.save(a);
            menuItem = menuItemRepository.save(menuItem);
            return menuItem;
        } catch (Exception e) {
            return null;
        }
    }


}
