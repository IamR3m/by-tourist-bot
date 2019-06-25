package by.alexkasko.bytouristbot.controller;

import by.alexkasko.bytouristbot.exception.ResourceNotFoundException;
import by.alexkasko.bytouristbot.model.City;
import by.alexkasko.bytouristbot.repository.CityRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    Logger logger;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> findAll() {
        return ResponseEntity.ok(cityRepository.findAll());
    }

    @PostMapping("/cities")
    public ResponseEntity create(@Valid @RequestBody City city) {
        return ResponseEntity.ok(cityRepository.save(city));
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", id)));
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @Valid @RequestBody City city) {
        City city1 = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        city1.setName(city.getName());
        city1.setDescription(city.getDescription());
        return ResponseEntity.ok(cityRepository.save(city1));
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        cityRepository.delete(city);
        return ResponseEntity.ok().build();
    }
}