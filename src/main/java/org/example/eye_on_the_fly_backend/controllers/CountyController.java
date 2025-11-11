package org.example.eye_on_the_fly_backend.controllers;

import org.example.eye_on_the_fly_backend.models.County;
import org.example.eye_on_the_fly_backend.repositories.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/counties")
@CrossOrigin(origins = "http://localhost:5173")
public class CountyController {
    @Autowired private CountyRepository countyRepository;
    @GetMapping("")
    public ResponseEntity<List<County>> getAllCounties() {
        List<County> allCounties = countyRepository.findAll();
        return new ResponseEntity<>(allCounties, HttpStatus.OK); }
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<County> addNewCounty(@RequestBody County newCounty) {
        County savedCounty = countyRepository.save(newCounty);
        return new ResponseEntity<>(savedCounty, HttpStatus.CREATED);
    }


}
