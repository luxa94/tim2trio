package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Guest;
import rs.isa.mrs.trio.iceipice.model.Reservation;
import rs.isa.mrs.trio.iceipice.model.dto.GetReservationDTO;
import rs.isa.mrs.trio.iceipice.model.dto.OrderItemDTO;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationDTO;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationWaiterDTO;
import rs.isa.mrs.trio.iceipice.repository.GuestRepository;
import rs.isa.mrs.trio.iceipice.repository.ReservationRepository;
import rs.isa.mrs.trio.iceipice.services.GradeService;
import rs.isa.mrs.trio.iceipice.services.ReservationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nina on 26-May-16.
 */
@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;

    @Autowired
    GradeService gradeService;

    @Autowired
    GuestRepository guestRepository;

    @RequestMapping(value = "/reservations/all/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllReservations(@PathVariable long id) {
        List<ReservationDTO> reservations = reservationService.getGuestsReservations(id);

        for(ReservationDTO res : reservations){
            boolean isGraded = gradeService.userHasGraded(id, res.getId());
            res.setGraded(isGraded);
        }

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @RequestMapping(value = "/reservation/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneReservation(@PathVariable long id) {
        final Reservation reservation = reservationRepository.findById(id);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/reservation/update", method = RequestMethod.POST)
    public ResponseEntity updateReservation(@RequestBody ReservationDTO reservationDTO) {

        final Reservation reservation = reservationService.editReservation(reservationDTO);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/reservation/create", method = RequestMethod.POST)
    public ResponseEntity createReservation(@RequestBody ReservationDTO reservationDTO) {

        System.out.println(reservationDTO);

        //set date object to 00:00
      //  Date date = reservationDTO.getDate();

        // ovde se nesto cudno desava
       // reservationDTO.setDate(new Date(date.getYear(), date.getMonth(), date.getDay()));

        final Reservation reservation = reservationService.createReservation(reservationDTO);
        if (reservation != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
    @RequestMapping(value = "/reservation/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteReservation(@PathVariable long id) {
        final Reservation reservation = reservationRepository.findById(id);
        if (reservation != null) {
            reservationService.deleteReservation(id);
            // ovde cu dodavati za fatch.lazy sta mi bude trebalo od gosta

            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Metoda koja kao parametar dobija getReservationDTO koji u sebi ima restaurantID i date
     * na osnovu kojih izvlaci rezervacije?
     *
     * Ulaz restaurant ID i danasnji datum
     * RestaurantTable 11
     *
     */
    @RequestMapping(value = "/reservation/get/by-restaurant/by-date", method = RequestMethod.POST)
    public ResponseEntity getReservationsForRestaurant(GetReservationDTO dto) {
        List<Reservation> reservations = reservationService.getReservationByRestaurantAndDate(dto.getRestaurantId(), dto.getReservationDate());
        List<ReservationDTO> dtoReservations = new ArrayList<>();
        for(Reservation res : reservations) {
            dtoReservations.add(new ReservationDTO(res));
        }
        return new ResponseEntity<>(dtoReservations, HttpStatus.OK);
    }

    List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();

    @RequestMapping(value = "/reservations/create", method = RequestMethod.POST)
    public ResponseEntity createFromWaiterReservation(@RequestBody ReservationWaiterDTO reservationWaiterDTO) {
        final Reservation reservation = reservationService.createReservation(reservationWaiterDTO);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/reservations/waiter/all/{id}")
    public ResponseEntity getReservationsForWaiter(@PathVariable long id) {
        return new ResponseEntity<>(reservationService.reservationsForWaiter(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/orderItems/newForReservation/{id}", method = RequestMethod.POST)
    public void addOrderToReservation(@PathVariable long id, @RequestBody OrderItemDTO orderItemDTO) {
        reservationService.addOrderToReservation(id, orderItemDTO);
    }

    @RequestMapping(value = "/reservations/close/{id}", method = RequestMethod.GET)
    public ResponseEntity closeReservation(@PathVariable long id) {
        return new ResponseEntity<>(reservationService.closeService(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/cook/{id}", method = RequestMethod.GET)
    public ResponseEntity getCooksOrders(@PathVariable long id) {
        return new ResponseEntity<>(reservationService.findOrderForCook(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/cook/make/{cookId}/order/{orderId}", method = RequestMethod.POST)
    public ResponseEntity cookTakeOrder(@PathVariable long cookId, @PathVariable long orderId) {
        if (reservationService.cookTakeOrder(cookId, orderId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/orders/finish/{id}", method = RequestMethod.POST)
    public void cookFinishOrder(@PathVariable long id) {
        reservationService.finishOrder(id);
    }

    @RequestMapping(value = "/orders/serve/{id}", method = RequestMethod.POST)
    public void serveOrder(@PathVariable long id) {
        reservationService.serveOrder(id);
    }

}
