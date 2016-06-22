package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Cook;
import rs.isa.mrs.trio.iceipice.model.CookShift;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.Shift;
import rs.isa.mrs.trio.iceipice.model.dto.CookDTO;
import rs.isa.mrs.trio.iceipice.model.dto.CookShiftDTO;
import rs.isa.mrs.trio.iceipice.repository.CookRepository;
import rs.isa.mrs.trio.iceipice.repository.CookShiftRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;
import rs.isa.mrs.trio.iceipice.repository.ShiftRepository;
import util.DateUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nina on 22-Apr-16.
 */
@Service
public class CookService {

    @Autowired
    CookRepository cookRepository;

    @Autowired
    BaseUserService baseUserService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    CookShiftRepository cookShiftRepository;


    public Cook editCook(CookDTO cookDTO) {
        Cook cook = cookRepository.findById(cookDTO.getId());
        updateCook(cook, cookDTO);

        try {
            cook = cookRepository.save(cook);
            return cook;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateCook(Cook cook, CookDTO cookDTO) {
        baseUserService.updateChangeable(cook, cookDTO);
        baseUserService.extractData(cook,cookDTO);
        cook.setDressSize(cookDTO.getDressSize());
        cook.setFootwearSize(cookDTO.getFootwearSize());
        cook.setRestaurant(restaurantRepository.findById(cookDTO.getRestaurantId()));
    }

    public Cook addCook(CookDTO cookDTO) {
        Cook cook = new Cook();
        updateCook(cook, cookDTO);

        try {
            cook = cookRepository.save(cook);
            return cook;
        } catch (Exception e) {
            return null;
        }
    }

    public void createCookShift(CookShiftDTO cookShiftDTO){
        final Cook worker = cookRepository.getOne(cookShiftDTO.getWorkerId());
        final Restaurant restaurant = restaurantRepository.findById(cookShiftDTO.getRestaurantId());
        Set<CookShift> cookShifts = new HashSet<CookShift>();
        Set<Shift> shifts = new HashSet<Shift>();

        if(cookShiftDTO.getStartDate().before(cookShiftDTO.getEndDate())){
            Date currentDate = cookShiftDTO.getStartDate();
            Shift newShift;
            CookShift newCookShift;
            while (currentDate.before(cookShiftDTO.getEndDate())){
                newShift = new Shift(restaurant, currentDate, true, cookShiftDTO.getStartHour(), cookShiftDTO.getEndHour());
                shifts.add(newShift);
                newCookShift = new CookShift(worker,newShift);
                cookShifts.add(newCookShift);
                currentDate = DateUtil.addDays(currentDate,1);
            }
            shiftRepository.save(shifts);
            cookShiftRepository.save(cookShifts);
        }
    }

}
