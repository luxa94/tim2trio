package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.*;
import rs.isa.mrs.trio.iceipice.model.dto.GuestDTO;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationDTO;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;
import rs.isa.mrs.trio.iceipice.repository.MenuItemRepository;
import rs.isa.mrs.trio.iceipice.repository.ReservationRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;

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

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    MenuItemRepository menuItemRepository;


    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        updateReservation(reservation, reservationDTO);

        try{
            List<Guest> backup = reservation.getGuests();
            reservation.setGuests(null);
            reservation = reservationRepository.save(reservation);
            reservation.setGuests(backup);
            for(Guest guest: backup) {
                guest.getReservations().add(reservation);
                guestRepository.save(guest);
            }
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
        reservation.setRestaurant(restaurantRepository.findById(reservationDTO.getRestaurantId()));
        List<Guest> guests = new ArrayList<Guest>();
        for(GuestDTO guest: reservationDTO.getGuests()){
            guests.add(guestRepository.findById(guest.getId()));
        }
        reservation.setGuests(guests);


    }

    public List<ReservationDTO> getGuestsReservations(long id) {
        List<Reservation> allReservations = reservationRepository.findAll();
        List<Reservation> reservations = new ArrayList<>();
        for(Reservation res  : allReservations) {
            for(Guest guest : res.getGuests()){
                if(guest.getId() == id) {
                    reservations.add(res);
                }
            }
        }


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


            if(res.getRestaurant().getId() == id && res.getDate().getTime() == date.getTime()){
                restaurant_reservations_on_date.add(res);
            }

        }
        return restaurant_reservations_on_date;

    }

    public List<Reservation> getReservationByRestaurant(long id){

        List<Reservation> restaurant_reservations = new ArrayList<Reservation>();
        List<Reservation> reservations = reservationRepository.findAll();

        for(Reservation res : reservations){
            if(res.getRestaurant().getId() == id) {
                restaurant_reservations.add(res);
            }

        }
        return restaurant_reservations;

    }
}
