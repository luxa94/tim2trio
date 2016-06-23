package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.globals.OrderItemStatus;
import rs.isa.mrs.trio.iceipice.globals.ReservationStatus;
import rs.isa.mrs.trio.iceipice.model.*;
import rs.isa.mrs.trio.iceipice.model.dto.GuestDTO;
import rs.isa.mrs.trio.iceipice.model.dto.OrderItemDTO;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationDTO;
import rs.isa.mrs.trio.iceipice.model.dto.ReservationWaiterDTO;
import rs.isa.mrs.trio.iceipice.repository.*;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    AreaRepository areaRpoAreaRepository;

    @Autowired
    WaiterRepository waiterRepository;

    @Autowired
    CookRepository cookRepository;

    @Autowired
    BartenderRepository bartenderRepository;

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    private boolean areTablesFree(List<Long> tableIds, String startTime, String endTime, Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        final String reservationDate = dateFormat.format(date);

        List<Reservation> reservations = null;
        for (long id : tableIds) {
            final RestaurantTable table = restaurantTableRepository.findById(id);
            if (reservations == null) {
                reservations = reservationRepository.findByRestaurant_Id(table.getArea().getRestaurant().getId());
            }

            for (Reservation reservation : reservations) {
                if (reservation.getRestaurant_tables().contains(table) && ReservationStatus.CREATED.equals(reservation.getStatus()) && reservationDate.equals(dateFormat.format(reservation.getDate()))) {
                    if ( (startTime.compareTo(reservation.getStart_hour()) >= 0 && startTime.compareTo(reservation.getEnd_hour()) < 0) ||
                            (endTime.compareTo(reservation.getStart_hour()) > 0 && endTime.compareTo(reservation.getEnd_hour()) <= 0) ) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    public synchronized Reservation createReservation(ReservationDTO reservationDTO) {

        if (!areTablesFree(reservationDTO.getTableIds(), reservationDTO.getStart_hour(), reservationDTO.getEnd_hour(), reservationDTO.getDate())) {
            return null;
        }

        Reservation reservation = new Reservation();
        updateReservation(reservation, reservationDTO);


        reservation.setRestaurant_tables(new HashSet<>(restaurantTableRepository.findAll(reservationDTO.getTableIds())));
        try {
            List<Guest> backup = reservation.getGuests();
            // reservation.setGuests(null);
            ///////////////////////////////// // reservation = reservationRepository.save(reservation);
            reservation.setGuests(backup);
            reservation = reservationRepository.save(reservation);
            List<Guest> backups2 = new ArrayList<Guest>();
            backups2.addAll(backup);
            for (int i = 0; i < backups2.size(); i++) {
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
        if (reservationDTO.getOrders() == null || reservationDTO.getOrders().size() == 0) {
            return;
        }
        try {
            Order order = new Order();
            order.setReservation(reservation);
        //    order.setRestaurantTables(reservation.getRestaurant_tables());
            order = orderRepository.save(order);
            List<OrderItem> orderItems = new ArrayList<>();
            // orders.add(order);
            reservation.setOrders(orderItems);

            for (OrderItemDTO dto : reservationDTO.getOrders()) {
                OrderItem item = new OrderItem();
                item.setAmount(dto.getAmount());
                MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId());
                item.setMenuItem(menuItem);
                item.setReservation(reservation);
                item.setOrder(order);
                item = orderItemRepository.save(item);
                reservation.getOrders().add(item);
                reservation = reservationRepository.save(reservation);

            }
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Reservation editReservation(ReservationDTO reservationDTO) {

            Reservation reservation = reservationRepository.findById(reservationDTO.getId());
            reservation = clearRelationships(reservation);
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

            reservation = reservationRepository.save(reservation);
            return reservation;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateReservation(Reservation reservation, ReservationDTO reservationDTO) {


        System.out.println("jeb seeeeen");

        reservation.setDate(reservationDTO.getDate());
        reservation.setStart_hour(reservationDTO.getStart_hour());
        reservation.setEnd_hour(reservationDTO.getEnd_hour());
        reservation.setRestaurant(restaurantRepository.findById(reservationDTO.getRestaurantId()));
        List<Guest> guests = new ArrayList<Guest>();
        for (GuestDTO guest : reservationDTO.getGuests()) {
            guests.add(guestRepository.findById(guest.getId()));
        }
        reservation.setGuests(guests);
        List<OrderItem> orders = new ArrayList<OrderItem>();
        for (OrderItemDTO order : reservationDTO.getOrders()) {
            //  orders.add(guestRepository.findById());
        }
        reservation.setOrders(orders);

    }

    public List<ReservationDTO> getGuestsReservations(long id) {
        List<Reservation> allReservations = reservationRepository.findAll();
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation res : allReservations) {
            for (Guest guest : res.getGuests()) {
                if (guest.getId() == id) {
                    reservations.add(res);
                }
            }
        }


        List<ReservationDTO> dtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationDTO resDTO = new ReservationDTO(reservation);


            List<OrderItem> orders = orderItemRepository.findAll();
            List<OrderItemDTO> itemDtos = new ArrayList<>();
            for (OrderItem item : orders) {
                if (item.getReservation().getId() == reservation.getId()) {
                    itemDtos.add(new OrderItemDTO(item));
                }
            }

            resDTO.setOrders(itemDtos);
            dtos.add(resDTO);

        }

        return dtos;

    }


    public List<Reservation> getReservationByRestaurantAndDate(long id, Date date) {

        List<Reservation> restaurant_reservations_on_date = new ArrayList<Reservation>();
        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation res : reservations) {


            if (res.getRestaurant().getId() == id && res.getDate().getTime() == date.getTime()) {
                restaurant_reservations_on_date.add(res);
            }

        }
        return restaurant_reservations_on_date;

    }

    public List<Reservation> getReservationByRestaurant(long id) {

        List<Reservation> restaurant_reservations = new ArrayList<Reservation>();
        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation res : reservations) {
            if (res.getRestaurant().getId() == id) {
                restaurant_reservations.add(res);
            }

        }
        return restaurant_reservations;

    }

    private Reservation clearRelationships(Reservation res ) {


        List<Guest> guests = res.getGuests();
        if(guests != null) {
            for(int i = 0; i < guests.size(); i++) {
                Guest guest = guests.get(i);
                Iterator<Reservation> tableIterator = guest.getReservations().iterator();
                while(tableIterator.hasNext()) {
                    Reservation guestRes = tableIterator.next();
                    if(guestRes.getId() == res.getId()) {
                        tableIterator.remove();
                    }
                }
                guestRepository.save(guest);
            }
        }


        List<Order> orders = orderRepository.findAll();
        List<Order> needToBeRemoved = new ArrayList<Order>();
        for(int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if(order.getReservation().getId() == res.getId()) {
                needToBeRemoved.add(order);
            }
        }



        res = reservationRepository.save(res);
        res.setOrders(null);
        res = reservationRepository.save(res);
        List<OrderItem> orderItems = orderItemRepository.findAll();
        for(OrderItem oi : orderItems) {
            if(oi.getReservation().getId()==res.getId()) {
                oi.setReservation(null);
                OrderItem oi2 =  orderItemRepository.save(oi);
                orderItemRepository.delete(oi2);
            }
        }

        res.setOrders(null);
        res.setGuests(null);
        res.setRestaurant(null);
        res.setRestaurant_tables(null);
        res = reservationRepository.save(res);

        for(Order order : needToBeRemoved) {
            // remove da order items
            order.setReservation(null);
            Order fromDB = orderRepository.save(order);
            orderRepository.delete(fromDB);
        }


        res = reservationRepository.save(res);
        return res;

    }

    public void deleteReservation(long id){
        Reservation res = reservationRepository.findById(id);
        res.setOrders(null);

        List<Guest> guests = res.getGuests();
        for (int i = 0; i < guests.size(); i++) {
            Guest guest = guests.get(i);
            Iterator<Reservation> tableIterator = guest.getReservations().iterator();
            while (tableIterator.hasNext()) {
                Reservation guestRes = tableIterator.next();
                if (guestRes.getId() == res.getId()) {
                    tableIterator.remove();
                }
            }
            guestRepository.save(guest);
        }


        /***
         *
         *  ============>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
         */
      // List<OrderItem> orderItems = res.getOrders();
     //   for(OrderItem oi : orderItems) {
     //       orderItemRepository.delete(oi);
     //   }

        List<Order> orders = orderRepository.findAll();
        List<Order> needToBeRemoved = new ArrayList<Order>();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.getReservation().getId() == res.getId()) {
                needToBeRemoved.add(order);
            }
        }

        for (Order order : needToBeRemoved) {
            orderRepository.delete(order);
        }

        res.setGuests(null);
        res.setRestaurant(null);
        res.setRestaurant_tables(null);

        res = reservationRepository.save(res);
        reservationRepository.delete(res);


    }

    public Reservation createReservation(ReservationWaiterDTO reservationWaiterDTO) {
        Reservation reservation = new Reservation();
        final Date currentDate = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("HH:mm");
        final String startTime = sdf.format(currentDate);
        final Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.HOUR, 2);
        final String endTime = sdf.format(c.getTime());

        if (!areTablesFree(reservationWaiterDTO.getTableIds(), startTime, endTime, new Date())) {
            return null;
        }

        reservation.setDate(currentDate);
        reservation.setStart_hour(startTime);
        reservation.setEnd_hour(endTime);
        reservation.setRestaurant(restaurantRepository.findById(reservationWaiterDTO.getRestaurantId()));
        reservation.setRestaurant_tables(new HashSet<>(restaurantTableRepository.findAll(reservationWaiterDTO.getTableIds())));

        Order order = new Order();
        order.setRestaurantTables(reservation.getRestaurant_tables());

            reservation = reservationRepository.save(reservation);
            order.setReservation(reservation);
            order = orderRepository.save(order);

            for (OrderItemDTO dto : reservationWaiterDTO.getOrderItemDTOs()) {
                if (dto.getAmount() > 0) {
                    final OrderItem orderItem = new OrderItem();
                    orderItem.setAmount(dto.getAmount());
                    orderItem.setOrder(order);
                    orderItem.setMenuItem(menuItemRepository.findById(dto.getMenuItemId()));
                    orderItem.setReservation(reservation);

                    orderItemRepository.save(orderItem);
                    reservation.getOrders().add(orderItem);
                }
            }
            reservation = reservationRepository.save(reservation);
            return reservation;
    }

    public List<Reservation> reservationsForWaiter(long id) {
        final Waiter waiter = waiterRepository.findById(id);
        return getActiveReservationsForRestaurant(waiter.getRestaurant());
    }

    private List<Reservation> getActiveReservationsForRestaurant(Restaurant restaurant) {
        final List<Reservation> restaurantReservations = reservationRepository.findByRestaurant_Id(restaurant.getId());

        final List<Reservation> reservations = new ArrayList<>();

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 30);
        date = calendar.getTime();

        final String currentDate = dateFormat.format(date);
        final String currentTime = timeFormat.format(date);

        for (Reservation reservation : restaurantReservations) {
            if (currentDate.equals(dateFormat.format(reservation.getDate())) && ReservationStatus.CREATED.equals(reservation.getStatus())) {
                if (currentTime.compareTo(reservation.getStart_hour()) > 0) {
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

    public void addOrderToReservation(long resId, OrderItemDTO dto) {
        if (dto.getAmount() > 0) {
            final Reservation reservation = reservationRepository.findById(resId);
            final OrderItem orderItem = new OrderItem();
            final Order order = orderRepository.findByReservation(reservation).get(0);
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItemRepository.findById(dto.getMenuItemId()));
            orderItem.setAmount(dto.getAmount());
            orderItemRepository.save(orderItem);
            reservation.getOrders().add(orderItem);
            reservationRepository.save(reservation);
        }
    }

    public double closeService(long id) {
        double price = 0;

        final Reservation reservation = reservationRepository.findById(id);
        final List<OrderItem> orderItems = reservation.getOrders();

        for (OrderItem orderItem : orderItems) {
            price += orderItem.getAmount() * orderItem.getMenuItem().getPrice();
        }

        reservation.setStatus(ReservationStatus.FINISHED);
        reservationRepository.save(reservation);
        return price;
    }

    public List<OrderItem> findOrderForCook(long id) {
        final Cook cook = cookRepository.findById(id);
        final List<Reservation> reservations = getActiveReservationsForRestaurant(cook.getRestaurant());
        final List<OrderItem> orderItems = new ArrayList<>();

        for (Reservation reservation : reservations) {
            for (OrderItem orderItem : reservation.getOrders()) {
                if ("hrana".equals(orderItem.getMenuItem().getArticle().getArticleType().getName()) && (OrderItemStatus.NEW.equals(orderItem.getStatus()) ||
                        (OrderItemStatus.MAKING.equals(orderItem.getStatus()) && orderItem.getCook() != null && cook.getId() == orderItem.getCook().getId()))) {
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }

    public List<OrderItem> findOrderForBartender(long id) {
        final Bartender bartender = bartenderRepository.findById(id);
        final List<Reservation> reservations = getActiveReservationsForRestaurant(bartender.getRestaurant());
        final List<OrderItem> orderItems = new ArrayList<>();

        for (Reservation reservation : reservations) {
            for (OrderItem orderItem : reservation.getOrders()) {
                if ("pice".equals(orderItem.getMenuItem().getArticle().getArticleType().getName()) && (OrderItemStatus.NEW.equals(orderItem.getStatus()) ||
                        (OrderItemStatus.MAKING.equals(orderItem.getStatus()) && orderItem.getBartender() != null && bartender.getId() == orderItem.getBartender().getId()))) {
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }

    public synchronized boolean cookTakeOrder(long cookId, long orderId) {
        final Cook cook = cookRepository.findById(cookId);
        final OrderItem orderItem = orderItemRepository.findById(orderId);

        if (OrderItemStatus.NEW.equals(orderItem.getStatus())) {
            orderItem.setCook(cook);
            orderItem.setStatus(OrderItemStatus.MAKING);
            orderItemRepository.save(orderItem);
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean bartenderTakeOrder(long bartenderId, long orderId) {
        final Bartender bartender = bartenderRepository.findById(bartenderId);
        final OrderItem orderItem = orderItemRepository.findById(orderId);

        if (OrderItemStatus.NEW.equals(orderItem.getStatus())) {
            orderItem.setBartender(bartender);
            orderItem.setStatus(OrderItemStatus.MAKING);
            orderItemRepository.save(orderItem);
            return true;
        } else {
            return false;
        }
    }

    public void finishOrder(long id) {
        final OrderItem orderItem = orderItemRepository.findById(id);
        orderItem.setStatus(OrderItemStatus.DONE);
        orderItemRepository.save(orderItem);
    }

    public void serveOrder(long id) {
        final OrderItem orderItem = orderItemRepository.findById(id);
        orderItem.setStatus(OrderItemStatus.SERVED);
        orderItemRepository.save(orderItem);
    }
}
