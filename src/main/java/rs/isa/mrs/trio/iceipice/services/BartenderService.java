package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Bartender;
import rs.isa.mrs.trio.iceipice.model.dto.BartenderDTO;
import rs.isa.mrs.trio.iceipice.repository.BartenderRepository;

/**
 * Created by Sandra on 22.4.2016.
 */
@Service
public class BartenderService {

    @Autowired
    BartenderRepository bartenderRepository;

    @Autowired
    BaseUserService baseUserService;

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
        baseUserService.updateChangeable(bartender, bartenderDTO);
        bartender.setDressSize(bartenderDTO.getDressSize());
        bartender.setFootwearSize(bartenderDTO.getFootwearSize());
    }
}
