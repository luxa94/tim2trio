package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.globals.UserTypes;
import rs.isa.mrs.trio.iceipice.model.Provider;
import rs.isa.mrs.trio.iceipice.model.dto.ProviderDTO;
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

    @RequestMapping(value = "/provider/update", method = RequestMethod.POST)
    public ResponseEntity updateProvider(@RequestBody ProviderDTO providerDTO) {
        //System.out.println("USAO JE U METODU!!!");
        final Provider p = providerService.editProvider(providerDTO);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/provider/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneProvider(@PathVariable long id) {
        final Provider p = providerRepository.findById(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
