package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars" )
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car new_car = carManagerService.save(car);
        return new ResponseEntity<>(new_car, status);
    }

    @GetMapping(path="/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping(path="/cars/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable Long id) {
        HttpStatus status = HttpStatus.FOUND;
        Optional<Car> found_car = carManagerService.getCarDetails(id);
        return new ResponseEntity<>(found_car, status);
    }
}

