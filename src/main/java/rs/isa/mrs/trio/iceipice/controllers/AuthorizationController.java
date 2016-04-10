package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.BaseUser;
import rs.isa.mrs.trio.iceipice.repository.BaseUserRepository;

/**
 * Created by nikolalukic on 4/10/16.
 */

@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @Autowired
    BaseUserRepository baseUserRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(@RequestParam String email, @RequestParam String password) {
        final BaseUser baseUser = baseUserRepository.findByEmailAndPassword(email, password);

        if (baseUser != null) {
            return new ResponseEntity<>(baseUser, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

}
