package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.SystemManager;
import rs.isa.mrs.trio.iceipice.repository.SystemManagerRepository;

import java.util.Date;

/**
 * Created by nikolalukic on 4/17/16.
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    SystemManagerRepository systemManagerRepository;

    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public void fillBase() {
        try {
            SystemManager sm = new SystemManager("aaa", "aaa", "aaa", "aaa", "asdasd", new Date());
            systemManagerRepository.save(sm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
