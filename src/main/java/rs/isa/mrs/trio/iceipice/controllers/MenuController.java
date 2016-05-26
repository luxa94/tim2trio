package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.isa.mrs.trio.iceipice.model.Menu;
import rs.isa.mrs.trio.iceipice.repository.MenuRepository;

/**
 * Created by Sandra on 25.5.2016.
 */
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    @RequestMapping(value = "/menu/oneFromR/{idR}", method = RequestMethod.GET)
    public ResponseEntity getMenuFromRestaurant(@PathVariable long idR) {
        for (Menu m : menuRepository.findAll()) {
            if (m.getRestaurant().getId() == idR) {
                return new ResponseEntity<>(m, HttpStatus.OK);
            }
        }
        return null;
    }
}
