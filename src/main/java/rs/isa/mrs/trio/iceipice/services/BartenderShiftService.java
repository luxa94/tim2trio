package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.*;
import rs.isa.mrs.trio.iceipice.model.dto.BartenderShiftDTO;
import rs.isa.mrs.trio.iceipice.model.dto.ShiftDTO;
import rs.isa.mrs.trio.iceipice.repository.AreaRepository;
import rs.isa.mrs.trio.iceipice.repository.BartenderRepository;
import rs.isa.mrs.trio.iceipice.repository.BaseUserRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;
import util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sandra on 16.6.2016.
 */

//MISLIM DA CE OVO MOCI LEPO DA SE OBIRSE SVE
@Service
public class BartenderShiftService {
    //@Autowired
    //AreaRepository areaRepository;

    //@Autowired
   // BaseUserRepository baseUserRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    BartenderRepository bartenderRepository;

    public Set<BartenderShift> createBartenderShift(BartenderShiftDTO bartenderShiftDTO) {
        //final Area area = areaRepository.findById(shiftDTO.getAreaId());
        final Bartender worker = bartenderRepository.getOne(bartenderShiftDTO.getWorkerId());
        final Restaurant restaurant = restaurantRepository.findById(bartenderShiftDTO.getRestaurantId());
        Set<BartenderShift> shifts = new HashSet<BartenderShift>();

        //provera da li je startDate pre endDate
        if(bartenderShiftDTO.getStartDate().before(bartenderShiftDTO.getEndDate())){
            Date currentDate = bartenderShiftDTO.getStartDate();
            Shift newShift;
            while (currentDate.after(bartenderShiftDTO.getEndDate())){
                newShift = new Shift(restaurant, currentDate, true, bartenderShiftDTO.getStartHour(), bartenderShiftDTO.getEndHour());
                shifts.add(new BartenderShift(worker, new Shift(restaurant, currentDate, true, bartenderShiftDTO.getStartHour(), bartenderShiftDTO.getEndHour())));
                DateUtil.addDays(currentDate,1);
            }

            return shifts;
        }
        else{
            return null;
        }
    }

}

