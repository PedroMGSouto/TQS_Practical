package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarManagerService {

    @Autowired
    private CarRepository repo;

    public Optional<Car> getCarDetails(Long id) {
        return Optional.ofNullable(repo.findByCarId(id));
    }

    public List<Car> getAllCars(){
        return repo.findAll();
    }

    public Car save(Car c){
        return repo.save(c);
    }
}
