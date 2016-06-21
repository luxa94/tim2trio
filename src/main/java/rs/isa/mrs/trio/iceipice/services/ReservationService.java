package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.*;
import rs.isa.mrs.trio.iceipice.model.dto.GuestDTO;
import rs.isa.mrs.trio.iceipice.model.dto.OrderItemDTO;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationDTO;
import rs.isa.mrs.trio.iceipice.repository.*;

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

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        updateReservation(reservation, reservationDTO);

        try{
            List<Guest> backup = reservation.getGuests();
           // reservation.setGuests(null);
          ///////////////////////////////// // reservation = reservationRepository.save(reservation);
            reservation.setGuests(backup);
            reservation = reservationRepository.save(reservation);
            List<Guest> backups2 = new ArrayList<Guest>();
            backups2.addAll(backup);
           for(int i = 0; i < backups2.size(); i++) {
                Guest newGuest = guestRepository.findById(backups2.get(i).getId());
               newGuest.getReservations().add(reservation);
               guestRepository.save(newGuest);
            }

            reservation = reservationRepository.findById(reservation.getId());
            createOrderItems(reservationDTO, reservation);


            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createOrderItems(ReservationDTO reservationDTO, Reservation reservation) {
        if(reservationDTO.getOrders() == null || reservationDTO.getOrders().size() ==0) {
            return;
        }
        try {
            Order order = new Order();
            order.setReservation(reservation);
            order.setRestaurantTables(reservation.getRestaurant_tables());
            order = orderRepository.save(order);
            List<OrderItem> orderItems = new ArrayList<>();
           // orders.add(order);
            reservation.setOrders(orderItems);

            for(OrderItemDTO dto : reservationDTO.getOrders()) {
                OrderItem item = new OrderItem();
                item.setAmount(dto.getAmount());
                MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId());
                item.setMenuItem(menuItem);
                item.setReservations(reservation);
                item.setOrder(order);
                item  = orderItemRepository.save(item);
                reservation.getOrders().add(item);
                reservation = reservationRepository.save(reservation);

            }
            orderRepository.save(order);
        }catch(Exception e) {
            e.printStackTrace();
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
            ReservationDTO resDTO = new ReservationDTO(reservation);


            List<OrderItem> orders = orderItemRepository.findAll();
            List<OrderItemDTO> itemDtos = new ArrayList<>();
            for(OrderItem item : orders) {
                if(item.getReservations().getId() == reservation.getId()){
                    itemDtos.add(new OrderItemDTO(item));
                }
            }

            resDTO.setOrders(itemDtos);
            dtos.add(resDTO);

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
