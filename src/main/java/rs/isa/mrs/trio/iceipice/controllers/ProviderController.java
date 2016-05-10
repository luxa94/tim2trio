package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.globals.UserTypes;
import rs.isa.mrs.trio.iceipice.model.Provider;
import rs.isa.mrs.trio.iceipice.repository.ProviderRepository;
import rs.isa.mrs.trio.iceipice.services.ProviderService;

/**
 * Created by nikolalukic on 5/10/16.
 */
@RestController
@RequestMapping("/api")
public class ProviderController {

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProviderService providerService;

    @RequestMapping(value = "/provider/create", method = RequestMethod.POST)
    public ResponseEntity createProvider(@RequestBody Provider provider) {

        provider.setType(UserTypes.PROVIDER);
        provider = providerRepository.save(provider);
        return new ResponseEntity<>(provider, HttpStatus.OK);

    }

}
