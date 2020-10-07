package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity getWhiskiesByYear(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "distillery", required = false) String distillery
            ){

        if (distillery != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryNameAndAge(distillery, age), HttpStatus.OK);
        }else if (year != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByYear(year), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        }
    }

}
