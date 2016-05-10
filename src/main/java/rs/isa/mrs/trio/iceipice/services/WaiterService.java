package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Waiter;
import rs.isa.mrs.trio.iceipice.model.dto.WaiterDTO;
import rs.isa.mrs.trio.iceipice.repository.WaiterRepository;

/**
 * Created by Sandra on 22.4.2016.
 */
@Service
public class WaiterService {

    @Autowired
    WaiterRepository waiterRepository;

    public Waiter editWaiter(WaiterDTO waiterDTO) {
        Waiter waiter = waiterRepository.findById(waiterDTO.getId());
        updateWaiter(waiter, waiterDTO);

        try{
            waiter = waiterRepository.save(waiter);
            return waiter;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateWaiter(Waiter waiter, WaiterDTO waiterDTO) {
        waiter.setName(waiterDTO.getName());
        waiter.setSurname(waiterDTO.getSurname());
        waiter.setBirthDate(waiterDTO.getBirthDate());
        waiter.setPhoneNumber(waiterDTO.getPhoneNumber());
        waiter.setDressSize(waiterDTO.getDressSize());
        waiter.setFootwearSize(waiterDTO.getFootwearSize());
    }
}
