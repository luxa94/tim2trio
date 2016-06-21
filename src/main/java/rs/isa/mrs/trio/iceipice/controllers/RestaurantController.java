package rs.isa.mrs.trio.iceipice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.isa.mrs.trio.iceipice.model.Grade;
import rs.isa.mrs.trio.iceipice.model.Restaurant;
import rs.isa.mrs.trio.iceipice.model.dto.GradeDTO;
import rs.isa.mrs.trio.iceipice.model.dto.RestaurantDTO;
import rs.isa.mrs.trio.iceipice.repository.RestaurantManagerRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantRepository;
import rs.isa.mrs.trio.iceipice.repository.RestaurantTypeRepository;
import rs.isa.mrs.trio.iceipice.services.GradeService;
import rs.isa.mrs.trio.iceipice.services.RestaurantService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolalukic on 4/17/16.
 */
@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    GradeService gradeService;

    @Autowired
    RestaurantManagerRepository restaurantManagerRepository;

    @Autowired
    RestaurantTypeRepository restaurantTypeRepository;

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants/all", method = RequestMethod.GET)
    public ResponseEntity getAllRestaurants() {


        List<Restaurant> resta = restaurantRepository.findAll();
        List<RestaurantDTO> dtos = new ArrayList<>();
        for(Restaurant rest : resta) {
            List<GradeDTO> grades = gradeService.getAllGradesForRestaurant(rest);
            int finalGrade = calculateGrade(grades);
            int finalGradeMeal = calculateMealGrade(grades);
            int finalGradeWaiter = calculateWaiterGrade(grades);
            RestaurantDTO dto = new RestaurantDTO(rest);
            dto.setRestaurantGrades(grades);
            dto.setFinalGrade(finalGrade);
            dto.setFinalGradeMeal(finalGradeMeal);
            dto.setFinalGradeWaiter(finalGradeWaiter);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    private int calculateGrade(List<GradeDTO> grades) {

        int rez = 0;
        if(grades.size() == 0) {
            return 0;
        }
        for( GradeDTO g : grades){
            rez += g.getAtmosphere_grade();
        }

        rez = (int) Math.round((double)rez/grades.size());
        return rez;
    }

    private int calculateMealGrade(List<GradeDTO> grades) {

        int rez1 = 0;
        if(grades.size() == 0) {
            return 0;
        }
        for( GradeDTO g : grades){
            rez1 += g.getMeal_grade();
        }

        rez1 = (int) Math.round((double)rez1/grades.size());
        return rez1;
    }
    private int calculateWaiterGrade(List<GradeDTO> grades) {

        int rez2 = 0;
        if(grades.size() == 0) {
            return 0;
        }
        for( GradeDTO g : grades){
            rez2 += g.getWaiter_grade();
        }

        rez2 = (int) Math.round((double)rez2/grades.size());
        return rez2;
    }


    @RequestMapping(value = "/restaurant/one/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneRestaurant(@PathVariable long id) {
        final Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/restaurant/oneM/{idman}", method = RequestMethod.GET)
    public ResponseEntity getOneRestaurantFromManager(@PathVariable long idman) {
        long id = restaurantManagerRepository.findById(idman).getRestaurant().getId();
        final Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/restaurant/create", method = RequestMethod.POST)
    public ResponseEntity addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {

        final Restaurant restaurant = restaurantService.addRestaurant(restaurantDTO);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/restaurant/update", method = RequestMethod.POST)
    public ResponseEntity updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {

        final Restaurant restaurant = restaurantService.editRestaurant(restaurantDTO);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
