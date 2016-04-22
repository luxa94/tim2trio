package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.repository.WaiterRepository;

/**
 * Created by Sandra on 22.4.2016.
 */
@Service
public class WaiterService {

    @Autowired
    WaiterRepository waiterRepository;


}
