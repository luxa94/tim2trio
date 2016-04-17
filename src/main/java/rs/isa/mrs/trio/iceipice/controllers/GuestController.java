package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;

/**
 * Created by Nina on 17-Apr-16.
 */
@RestController
@RequestMapping("/api")
public class GuestController {

    @Autowired
    GuestRepository guestRepository;




}
