package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Bartender;
import rs.isa.mrs.trio.iceipice.model.BartenderShift;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.Shift;
import rs.isa.mrs.trio.iceipice.model.dto.BartenderDTO;
import rs.isa.mrs.trio.iceipice.model.dto.BartenderShiftDTO;
import rs.isa.mrs.trio.iceipice.repository.BartenderRepository;
import rs.isa.mrs.trio.iceipice.repository.BartenderShiftRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;
import rs.isa.mrs.trio.iceipice.repository.ShiftRepository;
import util.DateUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sandra on 22.4.2016.
 */
@Service
public class BartenderService {

    @Autowired
    BartenderRepository bartenderRepository;

    @Autowired
    BaseUserService baseUserService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    BartenderShiftRepository bartenderShiftRepository;

    @Autowired
    ShiftRepository shiftRepository;

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

    public void createBartenderShift(BartenderShiftDTO bartenderShiftDTO) {
        //final Area area = areaRepository.findById(shiftDTO.getAreaId());
        final Bartender worker = bartenderRepository.getOne(bartenderShiftDTO.getWorkerId());
        final Restaurant restaurant = restaurantRepository.findById(bartenderShiftDTO.getRestaurantId());
        Set<BartenderShift> bartenderShifts = new HashSet<BartenderShift>();
        Set<Shift> shifts = new HashSet<Shift>();

        //provera da li je startDate pre endDate
        if(bartenderShiftDTO.getStartDate().before(bartenderShiftDTO.getEndDate())){
            System.out.print("BARTENDER SERVICE ADD SHIFT\n");
            Date currentDate = bartenderShiftDTO.getStartDate();
            Shift newShift;
            BartenderShift newBartenderShift;
            while (currentDate.before(bartenderShiftDTO.getEndDate())){
                System.out.print("\n aaa");
                newShift = new Shift(restaurant, currentDate, true, bartenderShiftDTO.getStartHour(), bartenderShiftDTO.getEndHour());
                shifts.add(newShift);
                newBartenderShift = new BartenderShift(worker,newShift);
                bartenderShifts.add(newBartenderShift);
                currentDate = DateUtil.addDays(currentDate,1);
            }
            System.out.print("      shifts.length = " + shifts.size());
            shiftRepository.save(shifts);
            bartenderShiftRepository.save(bartenderShifts);
        }
    }

}
