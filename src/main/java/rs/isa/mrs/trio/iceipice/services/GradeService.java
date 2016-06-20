package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Grade;
import rs.isa.mrs.trio.iceipice.model.Reservation;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.dto.GradeDTO;
import rs.isa.mrs.trio.iceipice.repository.GradeRepository;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;
import rs.isa.mrs.trio.iceipice.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina on 19-Jun-16.
 */
@Service
public class GradeService {

    @Autowired
    ReservationService reservationService;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public Grade addGrade(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        grade.setMeal_grade(gradeDTO.getMeal_grade());
        grade.setWaiter_grade(gradeDTO.getWaiter_grade());
        grade.setAtmosphere_grade(gradeDTO.getAtmosphere_grade());
        grade.setMeal_comment(gradeDTO.getMeal_comment());
        grade.setWaiter_comment(gradeDTO.getWaiter_comment());
        grade.setAtmosphere_comment(gradeDTO.getAtmosphere_comment());
        grade.setGuest(guestRepository.findById(gradeDTO.getUserId()));
        grade.setReservation(reservationRepository.findById(gradeDTO.getReservationId()));



        try {
            grade = gradeRepository.save(grade);
            return grade;
        } catch (Exception e) {
            return null;
        }
    }


    public List<GradeDTO> getAllGradesForRestaurant(Restaurant rest) {

        List<GradeDTO> dtos = new ArrayList<>();
        List<Reservation> reservations = reservationService.getReservationByRestaurant(rest.getId());

        for(Reservation res: reservations){
            for(Grade grade : gradeRepository.findByReservation_id((res.getId()))){
                GradeDTO gradeDTO = new GradeDTO(grade);
                dtos.add(gradeDTO);
            }
        }
        return dtos;
    }

    // proveriti 
    public List<GradeDTO> getAllGradesForMeal(Restaurant rest) {

        List<GradeDTO> dtos = new ArrayList<>();
        List<Reservation> reservations = reservationService.getReservationByRestaurant(rest.getId());

        for(Reservation res: reservations){
            for(Grade grade : gradeRepository.findByReservation_id((res.getId()))){
                GradeDTO gradeDTO = new GradeDTO(grade);
                dtos.add(gradeDTO);
            }
        }
        return dtos;
    }

}
