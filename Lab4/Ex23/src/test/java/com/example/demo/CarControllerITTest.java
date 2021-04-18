package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarControllerITTest {
    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository car_repo;

    @AfterEach
    public void resetDB() {
        car_repo.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        Car c1 = new Car("pig", "bacon");
        c1.setCarId(1234L);
        ResponseEntity<Car> resp = restTemplate.postForEntity("/api/cars", c1, Car.class);

        List<Car> found = car_repo.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("pig");
    }


    @Test
    public void givenCars_whenGetAllCars_thenReturnCars()  {
        Car c1 = new Car("pig", "bacon");
        c1.setCarId(1234L);
        Car c2 = new Car("zebra", "stripes");
        c2.setCarId(4321L);

        car_repo.saveAndFlush(c1);
        car_repo.saveAndFlush(c2);

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("pig", "zebra");

    }

}