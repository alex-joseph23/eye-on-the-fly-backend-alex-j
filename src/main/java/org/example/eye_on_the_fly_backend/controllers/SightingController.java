package org.example.eye_on_the_fly_backend.controllers;
import org.example.eye_on_the_fly_backend.models.Sighting;
import org.example.eye_on_the_fly_backend.models.County;
import org.example.eye_on_the_fly_backend.repositories.CountyRepository;
import org.example.eye_on_the_fly_backend.repositories.SightingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/sightings")
@CrossOrigin(origins = "http://localhost:5173")
public class SightingController {
    @Autowired
    private SightingRepository sightingRepository;
    @Autowired
    private CountyRepository countyRepository;

    @GetMapping("")
    public ResponseEntity<List<Sighting>> getAllSightings() {
        List<Sighting> allSightings = sightingRepository.findAll();
        return new ResponseEntity<>(allSightings, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sighting> getSightingById(@PathVariable Long id) throws NoResourceFoundException {
        return sightingRepository.findById(id)
                .map(sighting -> new ResponseEntity<>(sighting, HttpStatus.OK))
                .orElseThrow(() -> new NoResourceFoundException(HttpMethod.GET, "/api/sightings/" + id));
    }

    @PostMapping(value = "/add", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Sighting> addNewSighting(@RequestBody Sighting newSighting) {
        String countyName = newSighting.getCounty().getName().trim();
        countyName = countyName.substring(0, 1).toUpperCase() + countyName.substring(1).toLowerCase();
        County existingCounty = countyRepository.findByNameIgnoreCase(countyName);
        if (existingCounty == null) {
            existingCounty = new County(countyName);
            existingCounty = countyRepository.save(existingCounty);
        }
        newSighting.setCounty(existingCounty);
        Sighting savedSighting = sightingRepository.save(newSighting);
        return new ResponseEntity<>(savedSighting, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sighting> updateSighting(@PathVariable Long id, @RequestBody Sighting updatedSighting)
    throws NoResourceFoundException {
        Sighting sighting = sightingRepository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException(HttpMethod.PUT, "/api/sightings/" + id));


                    sighting.setDescription(updatedSighting.getDescription());

                    Sighting savedSighting = sightingRepository.save(sighting);
                    return new ResponseEntity<>(savedSighting,HttpStatus.OK);
                }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteSighting(@PathVariable Long id) throws NoResourceFoundException {
        sightingRepository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException(HttpMethod.DELETE, "/api/sightings/" + id));
        sightingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);}

    @GetMapping("/count-by-county")
    public ResponseEntity<Map<String, Integer>> getSightingCountByCounty() {
        List<Sighting> sightings = sightingRepository.findAll();
        Map<String, Integer> countByCounty = new HashMap<>();

        for (Sighting sighting : sightings) {
            String countyName = sighting.getCounty().getName();
            countByCounty.put(countyName, countByCounty.getOrDefault(countyName, 0) + 1);
        }

        return new ResponseEntity<>(countByCounty, HttpStatus.OK);
    }
}