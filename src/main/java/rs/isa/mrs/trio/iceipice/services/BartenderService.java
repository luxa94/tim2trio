package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Bartender;
import rs.isa.mrs.trio.iceipice.model.Cook;
import rs.isa.mrs.trio.iceipice.model.dto.BartenderDTO;
import rs.isa.mrs.trio.iceipice.model.dto.CookDTO;
import rs.isa.mrs.trio.iceipice.repository.BartenderRepository;
import rs.isa.mrs.trio.iceipice.repository.CookRepository;

/**
 * Created by Sandra on 22.4.2016.
 */
@Service
public class BartenderService {

    @Autowired
    BartenderRepository bartenderRepository;

    public Bartender editBartender(BartenderDTO bartenderDTO) {
        Bartender bartender = bartenderRepository.findById(bartenderDTO.getId());
        updateBartender(bartender, bartenderDTO);

        try{
            bartender = bartenderRepository.save(bartender);
            return bartender;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateBartender(Bartender bartender, BartenderDTO bartenderDTO) {
        bartender.setName(bartenderDTO.getName());
        bartender.setSurname(bartenderDTO.getSurname());
        bartender.setBirthDate(bartenderDTO.getBirthDate());
        bartender.setPhoneNumber(bartenderDTO.getPhoneNumber());
        bartender.setDressSize(bartenderDTO.getDressSize());
        bartender.setFootwearSize(bartenderDTO.getFootwearSize());
    }
}
