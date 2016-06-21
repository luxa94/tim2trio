package rs.isa.mrs.trio.iceipice.controllers;

/**
 * Created by Nina on 19-Jun-16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.isa.mrs.trio.iceipice.model.Grade;
import rs.isa.mrs.trio.iceipice.model.dto.GradeDTO;
import rs.isa.mrs.trio.iceipice.repository.GradeRepository;
import rs.isa.mrs.trio.iceipice.services.GradeService;

@RestController
@RequestMapping("/api")
public class GradeController {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    GradeService gradeService;

    @RequestMapping(value = "/grade/all", method = RequestMethod.GET)
    public ResponseEntity getAllReviewsOfRestaurant() {
        return new ResponseEntity<>(gradeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/grade/create", method = RequestMethod.POST)
    public ResponseEntity addGrade(@RequestBody GradeDTO gradeDTO) {

        final Grade grade = gradeService.addGrade(gradeDTO);
        if (grade != null) {
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
