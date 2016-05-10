package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.repository.ProviderRepository;

/**
 * Created by nikolalukic on 5/10/16.
 */
@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;



}
