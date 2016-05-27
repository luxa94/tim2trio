package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.repository.ArticleTypeRepository;

/**
 * Created by Sandra on 25.5.2016.
 */
@RestController
@RequestMapping("/api")
public class ArticleTypeController {
    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @RequestMapping(value = "/articleTypes/all", method = RequestMethod.GET)
    public ResponseEntity getAllArticleTypes() {
        return new ResponseEntity<>(articleTypeRepository.findAll(), HttpStatus.OK);
    }
}
