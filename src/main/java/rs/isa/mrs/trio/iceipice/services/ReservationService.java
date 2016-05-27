package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Reservation;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationDTO;
import rs.isa.mrs.trio.iceipice.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina on 26-May-16.
 */
@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        updateReservation(reservation, reservationDTO);

        try{
            reservation = reservationRepository.save(reservation);
            return reservation;
        } catch (Exception e) {
            return null;
        }
    }

    public Reservation editReservation(ReservationDTO reservationDTO) {
            Reservation reservation = reservationRepository.findById(reservationDTO.getId());
            updateReservation(reservation, reservationDTO);

        try{
            reservation = reservationRepository.save(reservation);
            return reservation;
        } catch (Exception e) {
            return null;
        }
    }
    private void updateReservation(Reservation reservation, ReservationDTO reservationDTO) {
        reservation.setDate(reservationDTO.getDate());
        reservation.setStart_hour(reservationDTO.getStart_hour());
        reservation.setEnd_hour(reservationDTO.getEnd_hour());
        reservation.setDate(reservationDTO.getDate());


    }

    public List<ReservationDTO> getGuestsReservations(long id) {
        List<Reservation> reservations = reservationRepository.findByGuest_Id(id);
        List<ReservationDTO> dtos = new ArrayList<>();

        for(Reservation reservation : reservations) {
            dtos.add(new ReservationDTO(reservation));
        }

        return dtos;

    }
}
