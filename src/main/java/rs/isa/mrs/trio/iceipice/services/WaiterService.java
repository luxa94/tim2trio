package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.*;
import rs.isa.mrs.trio.iceipice.model.dto.WaiterDTO;
import rs.isa.mrs.trio.iceipice.model.dto.WaiterShiftDTO;
import rs.isa.mrs.trio.iceipice.repository.*;
import util.DateUtil;

import java.util.*;

/**
 * Created by Sandra on 22.4.2016.
 */
@Service
public class WaiterService {

    @Autowired
    WaiterRepository waiterRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ShiftRepository shiftRepository;

    @Autowired
    WaiterShiftRepository waiterShiftRepository;

    @Autowired
    AreaRepository areaRepository;

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

    public void createWaiterShift(WaiterShiftDTO waiterShiftDTO) {

        final Waiter worker = waiterRepository.getOne(waiterShiftDTO.getWorkerId());
        final Restaurant restaurant = restaurantRepository.findById(waiterShiftDTO.getRestaurantId());
        Set<WaiterShift> waiterShifts = new HashSet<WaiterShift>();
        Set<Shift> shifts = new HashSet<Shift>();

        Set<Area> areas = new HashSet<Area>();
        for(int i=0; i<waiterShiftDTO.getAreas().size(); i++){
            int id = waiterShiftDTO.getAreas().get(i);
            areas.add(areaRepository.findById(id));
        }

        //provera da li je startDate pre endDate
        if(waiterShiftDTO.getStartDate().before(waiterShiftDTO.getEndDate())){
            //System.out.print("BARTENDER SERVICE ADD SHIFT\n");
            Date currentDate = waiterShiftDTO.getStartDate();
            Shift newShift;
            WaiterShift newWaiterShift;
            while (currentDate.before(waiterShiftDTO.getEndDate())){
                System.out.print("\n aaa");
                newShift = new Shift(restaurant, currentDate, true, waiterShiftDTO.getStartHour(), waiterShiftDTO.getEndHour());
                shifts.add(newShift);
                newWaiterShift = new WaiterShift(worker,newShift);
                newWaiterShift.setAreas(areas);
                waiterShifts.add(newWaiterShift);
                currentDate = DateUtil.addDays(currentDate,1);
            }
            shiftRepository.save(shifts);
            waiterShiftRepository.save(waiterShifts);
        }
    }
}
