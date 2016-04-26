package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.SystemManager;
import rs.isa.mrs.trio.iceipice.model.dto.SystemManagerDTO;
import rs.isa.mrs.trio.iceipice.repository.SystemManagerRepository;
import rs.isa.mrs.trio.iceipice.services.SystemManagerService;

/**
 * Created by Nina on 26-Apr-16.
 */
@RestController
@RequestMapping("/api")
public class SystemManagerController {

    @Autowired
    SystemManagerRepository systemManagerRepository;

    @Autowired
    SystemManagerService systemManagerService;

    @RequestMapping(value = "/sysmanager/all", method = RequestMethod.GET)
    public ResponseEntity getAllSystemManagers() {
        return new ResponseEntity<>(systemManagerRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/sysmanager/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneSystemManager(@PathVariable long id) {
        final SystemManager systemManager = systemManagerRepository.findById(id);
        if (systemManager != null) {
            return new ResponseEntity<>(systemManager, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sysmanager/update", method = RequestMethod.POST)
    public ResponseEntity updateSystemManager(@RequestBody SystemManagerDTO systemManagerDTO) {

        final SystemManager systemManager = systemManagerService.editSystemManager(systemManagerDTO);
        if (systemManager != null) {
            return new ResponseEntity<>(systemManager, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
