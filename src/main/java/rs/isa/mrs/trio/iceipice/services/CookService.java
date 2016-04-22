package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Cook;
import rs.isa.mrs.trio.iceipice.model.Guest;
import rs.isa.mrs.trio.iceipice.model.dto.CookDTO;
import rs.isa.mrs.trio.iceipice.model.dto.GuestDTO;
import rs.isa.mrs.trio.iceipice.repository.CookRepository;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;

/**
 * Created by Nina on 22-Apr-16.
 */
@Service
public class CookService {

    @Autowired
    CookRepository cookRepository;

    public Cook editCook(CookDTO cookDTO) {
        Cook cook = cookRepository.findById(cookDTO.getId());
        updateCook(cook, cookDTO);

        try{
            cook = cookRepository.save(cook);
            return cook;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateCook(Cook cook, CookDTO cookDTO) {
        cook.setName(cookDTO.getName());
        cook.setSurname(cookDTO.getSurname());
        cook.setBirthDate(cookDTO.getBirthDate());
        cook.setPhoneNumber(cookDTO.getPhoneNumber());
        cook.setPhoneNumber(cookDTO.getDressSize());
        cook.setPhoneNumber(cookDTO.getFootwearSize());

    }
}
