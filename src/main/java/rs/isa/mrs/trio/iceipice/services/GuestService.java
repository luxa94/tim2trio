package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Guest;
import rs.isa.mrs.trio.iceipice.model.dto.GuestDTO;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;

/**
 * Created by Nina on 17-Apr-16.
 */
@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    BaseUserService baseUserService;

    public Guest editGuest(GuestDTO guestDTO) {
        Guest guest = guestRepository.findById(guestDTO.getId());
        updateGuest(guest, guestDTO);

        try {
            guest = guestRepository.save(guest);
            return guest;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateGuest(Guest guest, GuestDTO guestDTO) {
        baseUserService.updateChangeable(guest, guestDTO);
    }

    public Guest verify(long id) {
        try {
            Guest guest = guestRepository.findById(id);
            guest.setConfirmed(true);
            guest = guestRepository.save(guest);
            return guest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
