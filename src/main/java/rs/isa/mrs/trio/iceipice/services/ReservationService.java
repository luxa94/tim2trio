package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.Reservation;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.RestaurantTable;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationDTO;
import rs.isa.mrs.trio.iceipice.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import  java.util.Date;
import java.util.Set;

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



    public List<Reservation> getReservationByRestaurantAndDate(long id, Date date){

        List<Reservation> restaurant_reservations_on_date = new ArrayList<Reservation>();
        List<Reservation> reservations = reservationRepository.findAll();

        for(Reservation res : reservations){
            Set<RestaurantTable> tables = res.getRestaurant_tables();
            RestaurantTable table = tables.iterator().next();
            Restaurant restaurant = table.getArea().getRestaurant();
            if(restaurant.getId() == id && res.getDate().getTime() == date.getTime()){
                restaurant_reservations_on_date.add(res);
            }

        }
        return restaurant_reservations_on_date;

    }

    public List<Reservation> getReservationByRestaurant(long id){

        List<Reservation> restaurant_reservations = new ArrayList<Reservation>();
        List<Reservation> reservations = reservationRepository.findAll();

        for(Reservation res : reservations){
            Set<RestaurantTable> tables = res.getRestaurant_tables();
            RestaurantTable table = tables.iterator().next();
            Restaurant restaurant = table.getArea().getRestaurant();

            restaurant_reservations.add(res);

        }
        return restaurant_reservations;

    }
}
