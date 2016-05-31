package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.Menu;
import rs.isa.mrs.trio.iceipice.model.MenuItem;
import rs.isa.mrs.trio.iceipice.model.dto.MenuItemDTO;
import rs.isa.mrs.trio.iceipice.repository.MenuItemRepository;
import rs.isa.mrs.trio.iceipice.repository.MenuRepository;
import rs.isa.mrs.trio.iceipice.services.MenuItemService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sandra on 25.5.2016.
 */
@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuItemRepository  menuItemRepository;

    @Autowired
    MenuItemService menuItemService;

    @RequestMapping(value = "/menu/oneFromR/{idR}", method = RequestMethod.GET)
    public ResponseEntity getMenuFromRestaurant(@PathVariable long idR) {
        for (Menu m : menuRepository.findAll()) {
            if (m.getRestaurant().getId() == idR) {
                return new ResponseEntity<>(m, HttpStatus.OK);
            }
        }
        return null;
    }

    @RequestMapping(value = "/menuItems/allFromR/{idR}", method = RequestMethod.GET)
    public ResponseEntity getMenuItemsFromRestaurant(@PathVariable long idR){
        Menu menu = new Menu();
        Set<MenuItem> menuItems = new HashSet<>();
        for (Menu m : menuRepository.findAll()) {
            if (m.getRestaurant().getId() == idR) {
                menu = m;
                break;
            }
        }
        for (MenuItem mi : menuItemRepository.findAll()){
            if (mi.getMenu().getId() == menu.getId()){
                menuItems.add(mi);
            }
        }
        return new ResponseEntity(menuItems, HttpStatus.OK);
    }
    @RequestMapping(value = "/menuItem/create", method = RequestMethod.POST)
    public ResponseEntity createMenuItem(@PathVariable MenuItemDTO menuItemDTO){
        final MenuItem menuItem = menuItemService.addMenuItem(menuItemDTO);

        if (menuItem != null){
            return  new ResponseEntity<>(menuItem,HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
