package com.example.demo;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource( locations = "/application-integrationtest.properties")

public class Ex3Test{
    @LocalServerPort
    int randomServerPort;

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
        restTemplate.postForEntity("/api/cars", c1, Car.class);

        List<Car> found = car_repo.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("bacon");
    }


    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200()  {
        Car c1 = new Car("pig", "bacon"); c1.setCarId(1234L);
        Car c2 = new Car("zebra", "stripes");c2.setCarId(4321L);
        Car c3 = new Car("lion", "roar"); c3.setCarId(1111L);

        car_repo.saveAndFlush(c1);
        car_repo.saveAndFlush(c2);
        car_repo.saveAndFlush(c3);

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("pig", "zebra","lion");

    }

}